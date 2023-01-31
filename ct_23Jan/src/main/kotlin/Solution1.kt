class Solution1: Solution {
    override fun start() {
        val testCase = intArrayOf(
            1, 2, 3, 4
        )
        println(
            runningSum(testCase).joinToString()
        )
    }

    fun runningSum(nums: IntArray): IntArray {
        val runningSum = IntArray(nums.size)
        runningSum[0] = nums[0]

        for (i in 1 until nums.size) {
            runningSum[i] = runningSum[i - 1] + nums[i]
        }
        return runningSum
    }

    fun runtimeBest(nums: IntArray): IntArray {
        val sumArray = IntArray(nums.size)
        nums.foldIndexed(0) { index, acc, i ->
            sumArray[index] = acc + i
            sumArray[index]
        }

        return sumArray
    }
}