class RotateArray : Solution {
    override fun start() {
        println("RotateArray")
        val ans = sol2(
            intArrayOf(
                0, 1, 2, 3, 4, 5,
            ),
            2
        )
        println(ans.joinToString())
    }

    fun miss1(nums: IntArray, k: Int) {
        // 매번 출력하는걸로 오해했음
        val numList = nums.toMutableList()
        for (i in 0 until k) {
            val last = numList.removeLast()
            numList.add(0, last)
            println("rotate ${i + 1} steps to the right : ${numList.joinToString()}")
        }
    }

    fun sol1(nums: IntArray, k: Int): IntArray {
        // Time O(1) Space O(n)
        val lastIndex = nums.size - 1
        val rotate = k % nums.size

        if (rotate == 0 || lastIndex == 0) {
            return nums
        }
        val forePart = nums.slice(lastIndex - rotate + 1..lastIndex)
        val backPart = nums.slice(0..lastIndex - rotate)

        return (forePart + backPart).toIntArray()
    }

    fun sol2(nums: IntArray, k: Int): IntArray {
        // Time O(n) Space O(n)
        val size = nums.size
        val rotate = k % nums.size
        val result = IntArray(size)

        for (i in nums.indices) {
            val newNum = nums[(i - rotate + size) % size] // 음수도 가져올 수 있게됨
            result[i] = newNum
        }

        return result
    }

    fun sol3(nums: IntArray, k: Int): IntArray {
        // change input : Time O(n) Space O(1)
        // TODO : k가 nums.size의 약수이면 옮기지 않는 구간이 생긴다.
        val size = nums.size
        val rotate = k % nums.size

        var preIndex = 0
        var postIndex = rotate
        var preNum = nums[preIndex]
        var postNum = nums[postIndex]

        for (notUse in nums.indices) {
            // preNum을 postIndex에 넣는 것을 반복한다.
            nums[postIndex] = preNum

            // index 갱신
            preIndex = postIndex
            postIndex = (preIndex + rotate) % size

            // 값 저장
            preNum = postNum
            postNum = nums[postIndex]
        }

        return nums
    }

    fun sol4(nums: IntArray, k: Int): Unit {
        // 반드시 nums를 바꿔야한다는걸 깨달음
        // rotate 만큼의 공간을 사용해서 기존 정보를 옮기자
    }

    fun otherSol1(nums: IntArray, k: Int) {
        val rotate = k % nums.size
        reverse(nums, 0, nums.size - 1)
        reverse(nums, 0, rotate - 1)
        reverse(nums, rotate, nums.size - 1)
    }

    private fun reverse(nums: IntArray, _start: Int, _end: Int) {
        var start = _start
        var end = _end
        while (start < end) {
            val temp = nums[start]
            nums[start] = nums[end]
            nums[end] = temp
            start++
            end--
        }
    }
}