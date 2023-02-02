fun getNbyMArray(n: Int, m: Int): Array<IntArray> {
    val table = Array(n) { IntArray(m) }
    for (i in 0 until n) {
        val line = readln().split(" ")
        for (j in 0 until m) {
            table[i][j] = line[j].toInt()
        }
    }
    return table
}

fun getArrayWithLength(): IntArray {
    val input = readLine()?.split(" ") ?: return intArrayOf()
    val size = input[0].toIntOrNull() ?: return intArrayOf()

    val arr = IntArray(size)
    for (i in 1..size) {
        arr[i - 1] = input[i].toInt()
    }
    return arr
}