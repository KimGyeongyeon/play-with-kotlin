package week1

import java.util.*

fun main() {
    println(parseInput1())
}

fun parseInput1(): Int {
    val n = readln().toInt()
    val parents = readln().split(SPACE).map { it.toInt() }
    val delete = readln().toInt()

    val tree = makeTree(n, parents)
    return countTreeExcept(delete, tree)
}

/* ArrayList 대신 HashMap을 쓰면 실제 삭제도 구현할 수 있다 */
fun makeTree(n: Int, parents: List<Int>): ArrayList<Node> {
    val tree = ArrayList<Node>(n)
    for (num in 0 until n) {
        val parent = parents[num]
        tree.add(Node(num, parent, arrayListOf()))
    }

    for (num in 0 until n) {
        val parent = parents[num]
        if (parent in 0 until n) {
            tree[parent].children.add(num)
        }
    }
    return tree
}

fun countTreeExcept(except: Int, tree: ArrayList<Node>): Int {
    val stack = Stack<Node>()
    val root = tree.find { it.isRoot() }
    if (root == null || root.num == except) {
        return 0 // root에 대한 예외처리
    } else {
        stack.add(root)
    }

    var count = 0
    while (stack.isNotEmpty()) {
        // DFS를 하되, except는 제외시킨다.
        val curr = stack.pop()
        if (curr.isLeaf(except)) count++

        for (child in curr.children) {
            if (child != except) {
                // 빼는 노드는 방문하지 않는다.
                stack.add(tree[child])
            }
        }
    }

    return count
}

data class Node(
    val num: Int,
    val parent: Int,
    val children: ArrayList<Int>
) {
    fun isLeaf(except: Int): Boolean = children.isEmpty() || (children.size == 1 && children[0] == except)
    fun isRoot(): Boolean = (parent == -1)
}