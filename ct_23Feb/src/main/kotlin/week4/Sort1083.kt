package week4

import Solution

class Sort1083: Solution {
    override fun start() {
        parseInput().let {
            println(it.joinToString(SPACE))
        }
    }

    val SPACE = " "
    fun parseInput(): IntArray {
        val n = readln().toInt()
        val nums = readln().split(SPACE).map { it.toInt() }.toIntArray()
        val s = readln().toInt()
        return getLastElement(n, s, nums)
    }

    fun getLastElement(n: Int, swap: Int, arr: IntArray): IntArray {
        // 앞자리 숫자가 가장 커야한다.
        // 인접 원소만 교환할 수 있다.

        val result = arr.copyOf()
        var count = swap
        var sortedIndex = 0 // 0부터 sortedIndex는 내림차순으로 정렬되어있다.

        while (count > 0 && sortedIndex < n-1) {
            // 정렬안된 것 중 max를 앞으로 보낸다.

            var maxIndex = result.slice(sortedIndex until n).findMaxIndex()
            var distance = maxIndex - sortedIndex

            if (distance > count){
                // max값을 앞으로 가져올 수 없는 경우
                // -> 가능한 범위에서 가장 큰 값을 앞으로 보내도록 변수를 조정한다.
                maxIndex = result.slice(sortedIndex until sortedIndex + count).findMaxIndex()
                distance = maxIndex - sortedIndex
            }

            // swap 수행
            for (i in 0 until distance) {
                result.swap(maxIndex - i, maxIndex - i - 1)
                count--
            }

            result.joinToString(SPACE).let { println(it) }
            sortedIndex++
        }
        return result
    }

    fun List<Int>.findMaxIndex(): Int {
        var result = -1
        var max = Int.MIN_VALUE
        this.forEachIndexed { i, value ->
            if (max < value) {
                max = value
                result = i
            }
        }
        return result
    }

    fun IntArray.swap(i: Int, j: Int) {
        var tmp = this[i]
        this[i] = this[j]
        this[j] = tmp
    }
}