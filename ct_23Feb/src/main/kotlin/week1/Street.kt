package week1

import Solution
import getNbyMArray

class Street : Solution {
    override fun start() {
//        solution(
//            4,
//            3,
//            arrayOf(
//                intArrayOf(1, 100, 2),
//                intArrayOf(2, 2, 2),
//                intArrayOf(4, 1, 10),
//                intArrayOf(1000, 1, 1000)
//            )
//        ).let { println(it) }

        parseInput { n, m, arr ->
            solution(n, m, arr)
        }.let { println(it) }
    }

    fun parseInput(func: (Int, Int, Array<IntArray>) -> Int): Int {
        val NUMBER_OF_COLOR = 3
        val n = readln().toIntOrNull() ?: return -1
        val cost = getNbyMArray(n, NUMBER_OF_COLOR)

        return func(n, NUMBER_OF_COLOR, cost)
    }

    /* 23MB, 204ms */
    fun solution(n: Int, numOfColor: Int, cost: Array<IntArray>): Int {
        // 총 경우의 수는 3*2^(n-1)
        // Greedy는 사용할 수 없음 ex) 특정 노드가 지나치게 큰 경우
        // DP를 사용하기로 했음

        val minTable = Array(n) { IntArray(numOfColor) { Int.MAX_VALUE } }

        // init
        for (i in 0 until numOfColor) {
            minTable[0][i] = cost[0][i]
        }

        // calculate
        for (i in 1 until n) {
            for (color in 0 until numOfColor) {
                val preCost = minTable[i - 1].clone().toMutableList().also { it.removeAt(color) }
                minTable[i][color] = cost[i][color] + preCost.minOf { it }
            }
        }

        return minTable.last().minOf { it }
    }
}