package week3

import Solution
import java.util.*

/* https://www.acmicpc.net/problem/15657 */
/* 참고 : https://www.crocus.co.kr/1240 */
class NM8 : Solution {
    override fun start() {
        parseInput().forEach { println(it.joinToString(SPACE)) }
    }

    private val SPACE = " "
    fun parseInput(): List<Array<Int>> {
        val nm = readln().split(SPACE).map { it.toInt() }
        val sequence = readln().split(SPACE).map { it.toInt() }
        return findAscendingSequence(sequence, nm[1])
    }

    fun findAscendingSequence(pool: List<Int>, size: Int): List<Array<Int>> {
        return getNestedCombination(pool.sorted(), size)
    }

    fun getNestedCombination(pool: List<Int>, size: Int): List<Array<Int>> {
        val pick = Stack<Int>().apply { addAll(Array<Int>(size) { 0 }) }
        val result = mutableListOf<Array<Int>>()

        while (true) {
            val elem = Array<Int>(size) { i -> pool[pick[i]] }.let {
                result.add(it)
            }

            // 인덱스 갱신
            val endedIndex = Stack<Int>()
            for (i in pick.indices.reversed()) {
                if (pick[i] == pool.size - 1) {
                    pick.pop().let { endedIndex.push(it) }
                } else {
                    break
                }
            }

            if (endedIndex.isEmpty()) {
                pick[size - 1]++
            } else {
                val lastIndex = pick.size - 1
                if (lastIndex < 0) {
                    break
                } else {
                    pick[lastIndex]++
                    val head = pick.last()
                    while (endedIndex.isNotEmpty()) {
                        pick.add(head)
                        endedIndex.pop()
                    }
                }
            }
        }
        return result
    }

    // 이건 통상 생각하는 조합을 구현한것이다.
    fun getCombination(pool: List<Int>, size: Int): List<Array<Int>> {

        val pick = Stack<Int>().apply { addAll(Array<Int>(size) { it }) }
        val result = mutableListOf<Array<Int>>()

        while (true) {
            val elem = Array<Int>(size) { i -> pool[pick[i]] }.let {
                result.add(it)
            }

            // 인덱스 갱신
            val endedIndex = Stack<Int>() // 그냥 count: Int라고 해도 되었을 것 같다.
            for (i in pick.indices.reversed()) {
                if (pick[i] == pool.size - size + i) {
                    pick.pop().let { endedIndex.push(it) }
                } else {
                    break
                }
            }

            if (endedIndex.isEmpty()) {
                pick[size - 1]++
            } else {
                val lastIndex = pick.size - 1
                if (lastIndex < 0) {
                    break
                } else {
                    pick[lastIndex]++
                    var head = pick.last()
                    while (endedIndex.isNotEmpty()) {
                        head++
                        pick.add(head)
                        endedIndex.pop()
                    }
                }
            }
        }
        return result
    }

}