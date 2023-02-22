package week3

import Solution
import java.util.*

/* https://www.acmicpc.net/problem/15666 */
class NM12 : Solution {

    override fun start() {
        parseInput().forEach { println(it.joinToString(SPACE)) }
    }

    private val SPACE = " "
    
    fun parseInput(): List<Array<Int>> {
        val nm = readln().split(SPACE).map { it.toInt() }
        val sequence = readln().split(SPACE).map { it.toInt() }
        return findAscendingNestedSequence(sequence, nm[1])
    }

    fun findAscendingNestedSequence(pool: List<Int>, size: Int): List<Array<Int>> {
        return getNestedCombination(pool.sorted().distinct(), size) // NM8과의 유일한 차이
    }

    fun getNestedCombination(pool: List<Int>, size: Int): List<Array<Int>> {

        val pick = Stack<Int>().apply { addAll(Array<Int>(size) { 0 }) }
        val result = mutableListOf<Array<Int>>()

        while (true) {
            // 이 부분을 함수 외부로 아예 뺐으면 더 좋았을 것 같다.
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
}