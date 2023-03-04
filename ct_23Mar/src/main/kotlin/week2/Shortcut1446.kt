package week2

import java.lang.Integer.min

class Shortcut1446 {
    fun main() {
        println(
            "지름길"
        )
        getInput()
    }

    fun getInput(){
        val (n, d) = readln().split(" ").map { it.toInt() }
        val shortcuts = arrayListOf<Shortcut>()
        repeat(n) {
            val (s, e, c) = readln().split(" ").map { it.toInt() }
            shortcuts.add(Shortcut(s,e,c))
        }

        println(dp(shortcuts, d))
    }

    fun dp(shortcuts: List<Shortcut>, d: Int): Int {
        // n까지 오는 최소값에 대한 테이블을 만든다.
        val cost = Array<Int>(d+1){it}

        // 같은 거리를 적은 비용으로 가는 지름길을 우선선택.
        for(s in shortcuts.sortedBy { it.costPerMeter }) {
            // cost table 갱신
            if (s.end > d || s.costPerMeter > 1.0) {
                continue
            }
            val old = cost[s.end]
            val new = cost[s.start] + s.cost
            if (new < old) {
                // 지름길
                for (i in s.end .. d) {
                    val usingShortcut = new + (i-s.end)
                    val notUse = cost[i]
                    cost[i] = min(usingShortcut, notUse)
                }
            }

        }
        return cost.last()
    }

}

data class Shortcut(
    val start: Int,
    val end: Int,
    val cost: Int,
) {
    val costPerMeter: Double = cost.toDouble()/(end - start)
}