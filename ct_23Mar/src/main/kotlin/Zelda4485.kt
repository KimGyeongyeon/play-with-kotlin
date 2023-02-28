import java.lang.Math.min
import java.util.LinkedList

const val SPACE = " "

fun main() {
    val result = parseInput()
    for (i in result.indices) {
        println("Problem ${i+1}: ${result[i]}")
    }
}

fun parseInput(): List<Int> {
    val result = LinkedList<Int>()

    while (true) {
        val n = readln().toInt()
        if (n ==0) break

        val map = Array<Array<Int>>(n){ emptyArray()}
        for (i in 0 until n) {
            val input = readln().split(SPACE).map { it.toInt() }.toTypedArray()
            map[i] = input
        }

        solution(map).let {
            result.add(it)
        }
    }

    return result
}

fun solution(map: Array<Array<Int>>): Int {
    val n = map.size
    return dijkstraBF(map)[n-1][n-1]
}

fun dijkstraHeap(map: Array<Array<Int>>) {
    // PQ 이용한 구현
}

fun dijkstraBF(map: Array<Array<Int>>): Array<Array<Int>> {
    // 모든 경로 보는 구현
    val n = map.size
    val cost = Array(n){Array(n){Int.MAX_VALUE} } // 0,0에서 x,y까지의 비용
    cost[0][0] = map[0][0]

    for (i in 0 until n) {
        for (j in 0 until n) {
            // i,j까지 바로 가는 것 or 상하좌우 경우중 최소값 채택
            val neighbors = arrayOf(
                Pair(i -1, j), //up
                Pair(i+1, j), //down
                Pair(i, j-1), //left
                Pair(i, j+1),//right
            )

            val backCost = map[i][j]
            for (xy in neighbors) {
                if (xy.isValid(n)){
                    val oldCost = cost[i][j]
                    val frontCost = cost[xy.first][xy.second]
                    if (frontCost != Int.MAX_VALUE) {
                        cost[i][j] = min(oldCost, frontCost + backCost)
                    }
                }
            }
        }
    }
    println("-- Cost --")
    cost.forEach { println(it.joinToString()) }

    return cost
}

fun Pair<Int, Int>.isValid(n: Int): Boolean {
    return this.first in 0 until n && this.second in 0 until n
}