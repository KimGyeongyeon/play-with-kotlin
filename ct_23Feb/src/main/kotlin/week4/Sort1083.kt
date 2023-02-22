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

    fun getLastElement(n: Int, swap: Int, arr: IntArray) : IntArray {
        // 앞자리 숫자가 가장 커야한다.
        // 인접 원소만 교환할 수 있다.
        var result = arr.copyOf()
        for(count in 0 until swap) {
            for(i in 0 until n-1) {
                if(result[i] < result[i+1]) {
                    // swap
                    var smaller = result[i]
                    result[i] = result[i+1]
                    result[i+1] = smaller
                    break
                }
            }
        }
        return result
    }
}