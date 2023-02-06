package week1

import Solution
import java.util.*

class Radius : Solution {
    override fun start() {
        println(parseInput())
    }

    private val EMPTY = -1

    fun parseInput(): Int {
        val numOfNode = readLine()?.toIntOrNull() ?: return -1
        val graph = Array<Array<Int>>(numOfNode + 1) { Array<Int>(numOfNode + 1) { EMPTY } }

        for (curr in 1..numOfNode) {
            val input = readLine()?.split(" ") ?: return -2
            for (j in 0 until input.size / 2 - 1) {
                val neighborNode = input[1 + 2 * j].toIntOrNull() ?: return -3
                val distance = input[2 + 2 * j].toIntOrNull() ?: return -4
                graph.set(x = curr, y = neighborNode, value = distance)
            }
        }
        return soltion1(numOfNode, graph)
    }

    fun soltion1(v: Int, edge: Array<Array<Int>>): Int {
        // hint ) 트리는 경로가 유일함

        // 1. O(n^3) 방식
        // 새 노드 curr을 탐색할 때 예전 노드 pre를 기억해서
        // 모든 노드에 x대해 x-pre가 존재하면 pre-curr값을 더해서 x-curr을 갱신시키자.

        // 2. O(n^2) 방식 - dfs
        // 모든 노드에 대해 dfs를 수행해서 가장 먼 노드를 찾아낸다.
        // 여기서 나온 노드 두개간의 거리가 radius이다.

        val stack = Stack<Int>()
        val visited = HashSet<Int>()
        for (node in 1..v) { // tree라 없어도 될 것 같다.
            stack.add(node)
            var pre: Int? = null
            while (stack.isNotEmpty()) {
                val curr = stack.pop()
                if (visited.contains(curr)) {
                    continue
                }

                for (i in 1..v) {
                    val childEdge = edge[curr][i]
                    if (childEdge == EMPTY) {
                        continue
                    }
                    // 1. 연결된 노드 stack에 넣기
                    stack.add(i)
                    // 2. 거리 갱신

                }

                // 3. 방문 표시
                visited.add(curr)
            }
        }
        return 0
    }

    fun Array<Array<Int>>.set(x: Int, y: Int, value: Int) {
        if (x < this.size && y < this[0].size) {
            this[x][y] = value
            this[y][x] = value
        }
    }
}