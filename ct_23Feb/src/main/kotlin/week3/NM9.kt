package week3

import Solution
import java.util.*

class NM9() : Solution {
    private val SPACE = " "
    override fun start() {
        parseInput().forEach { println(it.joinToString(SPACE)) }
    }

    fun parseInput(): List<Array<Int>> {
        val nm = readln().split(SPACE).map { it.toInt() }
        val pool = readln().split(SPACE).map { it.toInt() }
        return getPermutation(pool.sorted(), nm[1]).distinctBy { li -> li.joinToString("") }
    }

    fun getPermutation(pool: List<Int>, size: Int): List<Array<Int>> {
        val result = mutableListOf<Array<Int>>()
        val pick = Stack<Int>().apply { addAll(0 until size) }
        val leftNums = PriorityQueue<Int>().apply { addAll(size until pool.size) }

        while (true) {
            result.add(Array(size) { pool[pick[it]] })

            // index 갱신
            for (i in pick.indices.reversed()) {
                val max = leftNums.lastOrNull() ?: -1
                if (pick[i] > max) {
                    pick.pop().let { leftNums.add(it) }
                } else {
                    break
                }
            }

            if (pick.isEmpty()) {
                // 종료조건
                break
            } else {
                // 끝숫자의 다음으로 큰 수를 찾는다.
                val currLast = pick.lastElement()
                var nextLast = currLast
                while (!leftNums.contains(nextLast)) {
                    nextLast++
                }
                // 예전 값과 새 값을 바꾼다.
                pick.pop().let { leftNums.add(it) }
                leftNums.remove(nextLast)
                pick.add(nextLast)

                // 남은 자리는 작은 숫자로 채운다.
                while (pick.size < size) {
                    leftNums.poll().let { pick.add(it) }
                }
            }
        }
        return result
    }
}