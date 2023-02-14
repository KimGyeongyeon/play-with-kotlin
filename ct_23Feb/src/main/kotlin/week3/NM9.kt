package week3

import Solution
import java.util.Stack

class NM9(): Solution {
    private val SPACE = " "
    override fun start() {
        parseInput().forEach{ println(it.joinToString(SPACE)) }
    }

    fun parseInput(): List<Array<Int>> {
        val nm = readln().split(SPACE).map{it.toInt()}
        val pool = readln().split(SPACE).map{it.toInt()}
        return getPermutation(pool.sorted(), nm[1]).distinctBy{li -> li.joinToString("")}
    }

    fun getPermutation(pool: List<Int>, size: Int): List<Array<Int>> {
        val result: MutableList<Array<Int>> = mutableListOf<Array<Int>>()
        val startIndex = List<Int>(size){it}
        var initialIndex = List<Int>(size){it}
        val pick = Stack<Int>().apply{addAll(startIndex)}
        val leftIndices = ArrayDeque<Int>().apply{addAll(size until pool.size)}

        while(true){
            // pool에서 pick 인덱스 정보에 따라 수열 뽑기
            result.add(Array(size){pool[pick[it]]})

            // index 갱신
            for(i in pick.indices.reversed()) {
                val index = pick.pop().also{leftIndices.add(it)}
                if(pick == initialIndex) {
                    // 끝에 다다랐음
                    pick.removeAt(i-1).let { leftIndices.add(it) }
                    continue
                } else {
                    break
                }
            }

            while(pick.size < size){
                leftIndices.removeFirst().let{ pick.add(it) }
            }

            // 종료조건 검사
            if(pick == initialIndex) {
                break
            }
        }

        return result
    }

}