// https://leetcode.com/explore/learn/card/binary-search/138/background/1038/
class BinarySearch : Solution {
    override fun start() {
        val ans = search(
            intArrayOf(
                1, 2, 3, 4, 5,
            ),
            target = 5
        )
        println(ans)
    }

    fun search(nums: IntArray, target: Int): Int {
        var mid = 0
        var start = 0
        var end = nums.size - 1

        while (true) {
            mid = (start + end) / 2
            val midValue = nums[mid]
            if (target == nums[mid]) {
                return mid
            } else if (start == end) {
                return -1
            } else if (target > nums[mid]) {
                start = mid + 1
            } else {
                end = mid
            }
        }
    }



}