package week1

fun main() {
    println(input())
}

fun input(): Int {
    val n = readln().toInt()
    val parents = readln().split(" ").map { it.toInt() }

    // 함수 호출
    val tree = makeTree(n, parents)
    val root = tree.find { it.isRoot() }
    return if (root != null) {
        bfs(root, tree)
    } else {
        0 // root does not exist
    }
}

fun bfs(n: Node, tree: ArrayList<Node>): Int {
    if (n.isLeaf(-1)) return 0

    val subTreeTimes = arrayListOf<Int>()
    for (c in n.children) {
        subTreeTimes.add(bfs(tree[c], tree))
    }
    return subTreeTimes
        .sortedDescending()
        .mapIndexed { index, i -> index + 1 + i } // 가장 연락이 오래 걸리는 부서부터 전화를 건다.
        .maxOf { it }
}

//data class Node(
//    val num: Int,
//    val parent: Int,
//    val children: ArrayList<Int>
//) {
//    fun isLeaf(): Boolean = children.isEmpty()
//    fun isRoot(): Boolean = (parent == -1)
//}
//
//fun makeTree(n: Int, parents: List<Int>): ArrayList<Node> {
//    val tree = ArrayList<Node>(n)
//    for (num in 0 until n) {
//        val parent = parents[num]
//        tree.add(Node(num, parent, arrayListOf()))
//    }
//
//    for (num in 0 until n) {
//        val parent = parents[num]
//        if (parent in 0 until n) {
//            tree[parent].children.add(num)
//        }
//    }
//    return tree
//}