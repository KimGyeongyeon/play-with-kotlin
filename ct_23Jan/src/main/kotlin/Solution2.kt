class Solution2: Solution {
    override fun start() {
        val ans = maximumWealth(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(100, 2, 3),
                intArrayOf(8, 4, 6)
            )
        )
        println(ans)
    }

    fun maximumWealth(accounts: Array<IntArray>): Int {
        return accounts.map { it.sum() }.sorted().last()
    }

    fun maximumWealth2(accounts: Array<IntArray>): Int {
        var max = -1
        for(i in 0 until accounts.size) {
            val sum = accounts[i].sum()
            if(max < sum) {
                max = sum
            }
        }
        return max
    }

    fun maximumWealth3(accounts: Array<IntArray>): Int {
        var max = -1
        for(element in accounts) {
            val sum = element.sum()
            if(max < sum) {
                max = sum
            }
        }
        return max
    }
}