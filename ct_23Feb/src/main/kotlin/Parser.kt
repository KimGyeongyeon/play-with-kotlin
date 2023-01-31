object Parser {
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
        return intArrayOf()
    }
}