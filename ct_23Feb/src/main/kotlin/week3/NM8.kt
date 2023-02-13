package week3

import Solution
import java.util.Stack

/* https://www.acmicpc.net/problem/15657 */
class NM8 : Solution {
    override fun start() {
        println("N과 M (8)")
        //parseInput().forEach { print(it.joinToString("") + SPACE) }
        getCombination(listOf(0, 1, 2, 3, 4, 5,), 4)
    }

    private val SPACE = " "
    fun parseInput(): List<Array<Int>> {
        val nm = readln().split(SPACE).map { it.toInt() }
        val sequence = readln().split(SPACE).map { it.toInt() }
        return findAscendingSequence(sequence, nm[1])
    }

    fun findAscendingSequence(pool: List<Int>, size: Int): List<Array<Int>> {
        return getCombination(pool, size)
    }

    fun getCombination(pool: List<Int>, size: Int): List<Array<Int>> {

        val pick = Stack<Int>().apply {addAll(0 until size)}
        val result = mutableListOf<Array<Int>>()

        while (pick.first() != pool.size - size) {
            val elem = Array<Int>(size) { i -> pool[pick[i]] }.let {
                result.add(it)
                println(it.joinToString())
            }
            for (i in pick.indices.reversed()) {
                // 지금 last랑 last 직접 인덱스에만 유효하다. stack을 쓸 필요가 있다.
                if (pick[i] == pool.size - size + i) {
                    // 6C3의 케이스에서 0,1,5를 선택한 경우
                    // 다음은 0,2,3이 되어야한다.
                    if (i == 0) {
                        pick[i]++
                        break
                    } else {
                        pick[i - 1]++
                        for (j in i until size) {
                            pick[j] = pick[j - 1] + 1
                        }
                        break
                    }
                } else {
                    // 6C3의 케이스에서 0,1,4를 선택한 경우
                    // 다음은 0,1,5를 선택해야한다.
                    pick[i]++
                    break
                }
            }
        }
        return result
    }
}