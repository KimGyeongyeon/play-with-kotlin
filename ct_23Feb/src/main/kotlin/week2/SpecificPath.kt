package week2

import Solution
import kotlin.math.min

class SpecificPath : Solution {

    override fun start() {
        println(parseInput())
//        val edges = arrayOf(
//            arrayOf(-2, -2, -2, -2, -2, -2),
//            arrayOf(-2, -2,  1, -2, -2, -2), //1
//            arrayOf(-2,  1, -2,  2, 10, -2), //2
//            arrayOf(-2, -2,  2, -2,  3, -2), //3
//            arrayOf(-2, -2, 10,  3, -2,  1), //4
//            arrayOf(-2, -2, -2, -2,  1, -2), //5
//        )
//
//        getDistance(1, 5, edges).let { println(it) }
//        getDistance(3, 5, edges).let { println(it) }
        pathCache.forEach{ it -> println(it.value.joinToString()) }
    }

    private val SPACE = " "
    private val FAIL = -1
    private val NOT_EXIST = -2
    private val INF = Int.MAX_VALUE

    private fun parseInput(): Int {
        val nm = readln().split(SPACE).map { it.toInt() }

        val edges = Array<Array<Int>>(nm[0] + 1) { Array(nm[0] + 1) { INF } }
        for (i in 0 until nm[1]) {
            val nodes = readln().split(SPACE).map { it.toInt() }
            val distance = nodes[2]
            edges[nodes[0]][nodes[1]] = distance
            edges[nodes[1]][nodes[0]] = distance
        }

        val waypoints = readln().split(SPACE).map { it.toInt() }

        return getShorterPath(nm[0], waypoints, edges)
    }

    private fun getShorterPath(n: Int, waypoints: List<Int>, edges: Array<Array<Int>>): Int {
        // (1-v1 + N-v2), (1-v2 + N-v1) 중에 작은거 하면 됨
        // v1 - v2는 고정이기 때문

        val root1 = try {
            getDistance(1, waypoints[0], edges) +
                    getDistance(waypoints[0], waypoints[1], edges) +
                    getDistance(waypoints[1], n, edges)
        } catch (e: Exception) {
            FAIL
        }

        val root2 = try {
            getDistance(1, waypoints[1], edges) +
                    getDistance(waypoints[1], waypoints[0], edges) +
                    getDistance(waypoints[0], n, edges)
        } catch (e: Exception) {
            FAIL
        }

        return if (root1 == FAIL && root2 == FAIL) {
            return FAIL
        } else if (root1 == FAIL) {
            return root2
        } else if (root2 == FAIL) {
            return root1
        } else {
            min(root1, root2)
        }
    }

    // 다익스트라
    private val pathCache: MutableMap<Int, Array<Int>> = mutableMapOf()
    private fun getDistance(origin: Int, dest: Int, edges: Array<Array<Int>>): Int {
        // 기존 정보 확인
        val cache = pathCache[origin]?.get(dest)
        if (cache != null) {
            return throwIfIntMax(cache)
        }

        // 초기화
        val node = edges.size - 1
        val pathLength = edges[origin].copyOf()
        pathLength[origin] = 0

        for (stop in 1..node) {
            if (stop == origin) continue
            for (end in 1..node) {
                if (stop == end) continue

                val directPath = pathLength[end]
                val path1 = pathLength[stop]
                val path2 = edges[stop][end]

                // 기존 path가 없다고 우회경로가 실패라는 보장은 없다...
                if (path1.isPathExist() && path2.isPathExist()) {
                    pathLength[end] = min(directPath, path1 + path2)
                }
            }
        }

        // 정보 저장
        pathCache[origin] = pathLength

        return throwIfIntMax(pathLength[dest])
    }

    private fun Int.isPathExist(): Boolean {
        return this != INF
    }

    private fun throwIfIntMax(num: Int): Int {
        if (num == INF) {
            throw Exception("Path does not exist")
        } else {
            return num
        }
    }
}