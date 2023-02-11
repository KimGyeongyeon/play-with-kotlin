package week2

import Solution
import java.lang.Math.min

class FloydWarshall : Solution {
    val NO = -2
    val INF = Int.MAX_VALUE

    override fun start() {
        println("플로이드 와샬")
        val node = 4 // 노드는 1번부터 있는 것으로 한다.
        val edges = arrayOf(
            arrayOf(NO, NO, NO, NO, NO),
            arrayOf(NO, 0, 5, NO, 8), //1
            arrayOf(NO, 7, 0, 9, NO), //2
            arrayOf(NO, 2, NO, 0, NO), //3
            arrayOf(NO, NO, NO, 3, 0), //4
        )
        getEveryPathDistance(edges).forEach { println(it.joinToString()) }
    }

    private fun getEveryPathDistance(edges: Array<Array<Int>>): Array<Array<Int>> {
        // 변수 초기화
        val size = edges.size // node + 1
        val path = edges.map { it.copyOf() }.toTypedArray()
        for (i in 1 until size) {
            path[i][i] = 0 // 자기 자신과의 거리는 0
        }

        // 거리 계산
        for (p in 1 until size) {
            // [x - p - y] 경로가 [x-y]보다 짧다면 갱신한다.
            for (start in 1 until size) {
                if (start == p) continue

                for (end in 1 until size) {
                    if (end == p || end == start) continue

                    val oldDistance = if (path[start][end].isExist()) path[start][end] else INF
                    val path1 = path[start][p]
                    val path2 = path[p][end]

                    if (path1.isExist() && path2.isExist()) {
                        path[start][end] = min(oldDistance, path1 + path2)
                    }
                }
            }
        }


        return path
    }

    fun Int.isExist(): Boolean = !(this == NO || this == INF)
}