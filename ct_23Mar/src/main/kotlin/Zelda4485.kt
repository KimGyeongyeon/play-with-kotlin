import java.util.*
import kotlin.math.min

const val SPACE = " "

/**
 * 다익스트라 알고리즘 - DP의 일종
 * 시작 노드와 다른 노드간의 비용을 table에 저장하고,
 * **table의 값이 가장 작은 노드부터** 방문하여 주변 노드로 가는 비용을 계산한다.
 * 노드는 중복해서 탐색하면 안된다.
 * */

fun main() {
    val result = parseInput()
    for (i in result.indices) {
        println("Problem ${i + 1}: ${result[i]}")
    }
}

fun parseInput(): List<Int> {
    val result = LinkedList<Int>()
    while (true) {
        val n = readln().toInt()
        if (n == 0) break

        val map = Array<Array<Int>>(n) { emptyArray() }
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
    return dijkstraHeap(map)[n - 1][n - 1]
}

fun dijkstraHeap(map: Array<Array<Int>>): Array<Array<Int>> {
    val d = Dijkstra(map)

    while (!d.isAllVisited()) {
        val shortest = d.getSmallestNode()
        if (shortest == null) {
            break
        } else {
            d.visit(shortest)
        }
    }

    return d.cost
}

class Dijkstra(private val edges: Array<Array<Int>>) {
    private val n = edges.size
    private val m = edges[0].size
    private val pq = PriorityQueue<Edge<Int>> { o1, o2 -> o1.value.compareTo(o2.value) }
    val cost = Array(n) { Array(m) { Int.MAX_VALUE } }
    private val visited = Array(n) { Array(m) { false } }

    init {
        pq.add(Edge(edges[0][0], 0, 0))
        cost[0][0] = edges[0][0]
    }

    fun getSmallestNode(): Edge<Int>? = if (pq.isEmpty()) null else pq.poll()

    private fun isValid(p: Pair<Int, Int>): Boolean = p.first in 0 until n && p.second in 0 until m

    private fun isVisited(p: Pair<Int, Int>): Boolean = visited[p.first][p.second]
    fun isAllVisited(): Boolean = visited.all { arr -> arr.all { it } }

    private fun Pair<Int, Int>.toEdge(map: Array<Array<Int>>): Edge<Int> {
        return Edge<Int>(map[first][second], first, second)
    }

    fun visit(curr: Edge<Int>) {
        val (v, x, y) = curr
        // 방문처리
        visited[x][y] = true

        // 연결된 노드의 cost 갱신 + PQ에 삽입
        val neighbors = arrayOf(
            Pair(x - 1, y), //up
            Pair(x + 1, y), //down
            Pair(x, y - 1), //left
            Pair(x, y + 1),//right
        )

        for (n in neighbors) {
            // 출발점 -> curr -> neighbor을 거치는 경로를 갱신한다.
            if (isValid(n) && !isVisited(n)) {
                val neighbor = n.toEdge(cost)
                val oldCost = cost[neighbor.x][neighbor.y]
                val way1 = cost[x][y]
                val way2 = edges[neighbor.x][neighbor.y]

                if (way1 != Int.MAX_VALUE) {
                    cost[neighbor.x][neighbor.y] = min(oldCost, way1 + way2)
                    pq.add(n.toEdge(cost))
                }
            }
        }
    }
}

data class Edge<T>(
    val value: T,
    val x: Int,
    val y: Int,
)