package week2

import java.lang.Integer.max
import java.lang.Integer.min

class KickDown1195 {
    val CONVEX = '2'

    fun main() {
        getInput()
    }

    fun getInput() {
        val gear = arrayListOf<String>()
        repeat(2) {
            gear.add(readln())
        }
        gear.sortBy { it.length }
        println(
            getMinLength(gear[0], gear[1])
        )
    }

    fun getMinLength(shorter: String, longer: String): Int {
        // 2끼리만 안만나면 된다.
        var minLength = Int.MAX_VALUE
        // longer fragment를 시작을 0으로해서 shorter fragment의 시작점을 정의하자
        for (start in -1 * shorter.length..longer.length) {
            var isMatch = true

            // shorter fragment를 기준으로 맞물릴 수 있는지 확인
            for (i in shorter.indices) {
                if (!longer.isIndexValid(start + i)) continue
                // 2끼리 맞물리면 실패
                if (shorter[i] == CONVEX && longer[start + i] == CONVEX) {
                    isMatch = false
                    break
                }
            }

            if (isMatch) {
                // length 계산해서 갱신
                val head = min(start, 0)
                val tail = max(start + shorter.length, longer.length)
                minLength = min(tail - head, minLength)
            }
        }

        return minLength
    }

    fun String.isIndexValid(i: Int): Boolean {
        return i in indices
    }
}