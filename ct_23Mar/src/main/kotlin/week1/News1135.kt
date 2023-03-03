package week1

fun main() {
    println("news")
    parseInput2()
}

fun parseInput2() {
    val n = readln().toInt()
    val parents = readln().split(" ").map { it.toInt() }

    val tree = makeTree(n, parents)
}

// depth가 깊은 서브트리부터 전화하는게 포인트!
//
fun treeHeight(n: Int, parents: List<Int>): Int {

    val internalNodes = hashSetOf<Int>()
    var max = 0

    for (i in 0 until n) {
        var count = 0
        var curr = i

        if (internalNodes.contains(curr)) continue

        while (parents[curr] != -1) {
            count++
            internalNodes.add(curr)
            curr = parents[curr]
        }
        if (max < count) max = count
    }

    return max
}

