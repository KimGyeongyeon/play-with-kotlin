package week2

import Solution
import kotlin.math.min

class SpecificPath : Solution {

    override fun start() {
        // println(parseInput())
        val edges = arrayOf(
            arrayOf(-2, -2, -2, -2, -2, -2),
            arrayOf(-2, -2,  1, -2, -2, -2), //1
            arrayOf(-2,  1, -2,  2, 10, -2), //2
            arrayOf(-2, -2,  2, -2,  3, -2), //3
            arrayOf(-2, -2, 10,  3, -2,  1), //4
            arrayOf(-2, -2, -2, -2,  1, -2), //5
        )

        getDistance(1, 5, edges).let { println(it) }
        getDistance(3, 5, edges).let { println(it) }
    }

    private fun parseInput(): Int {
        val nm = readln().split(SPACE).map { it.toInt() }

        val edges = Array<Array<Int>>(nm[0] + 1) { Array(nm[0] + 1) { NOT_EXIST } }
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
    private fun getDistance(arr: Int, dest: Int, edges: Array<Array<Int>>): Int {
        // 기존 정보 확인
        val cache = pathCache[arr]?.get(dest)
        if (cache != null) {
            return throwIfIntMax(cache)
        }

        // 초기화
        val queue = ArrayDeque<Int>()
        val visited = HashSet<Int>()
        val pathLength = Array<Int>(edges.size) { Int.MAX_VALUE }
        pathLength[arr] = 0
        queue.add(arr)

        while (queue.isNotEmpty()) {
            val currNode = queue.removeFirst()
            if (visited.contains(currNode)) continue

            // 이웃 노드 큐에 추가
            for (i in edges[currNode].indices) {
                if (edges[currNode][i] != NOT_EXIST) {
                    queue.add(i)

                    val oldLength = pathLength[i]
                    val newLength = pathLength[currNode] % Int.MAX_VALUE + edges[currNode][i] // overflow 방지

                    pathLength[i] = min(oldLength, newLength)
                }
            }
            // 방문처리
            visited.add(currNode)
        }

        // 정보 저장
        pathCache[arr] = pathLength

        return throwIfIntMax(pathLength[dest])
    }

    private fun throwIfIntMax(num: Int): Int {
        if (num == Int.MAX_VALUE) {
            throw Exception("Path does not exist")
        } else {
            return num
        }
    }

    companion object {
        private const val SPACE = " "
        private const val FAIL = -1
        private const val NOT_EXIST = -2
    }
}