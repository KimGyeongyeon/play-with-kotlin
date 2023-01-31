// https://leetcode.com/explore/featured/card/top-interview-questions-easy/127/strings/884/
class Atoi : Solution {
    override fun start() {
        println("words and 987")
        println(
            myAtoi("ords and 987")
        )

        println('4'.digitToInt())
    }

    private val SPACE = " "
    private val EMPTY = ""
    // Int.MAX_VALUE //  2,147,483,647
    // Int.MIN_VALUE // -2,147,483,648

    fun myAtoi(s: String): Int {
        val sign = if (s.contains("-")) -1 else 1
        val str = trimNonNumber(s)
        println("str : $str")

        // Handle overflow
        if (isOverflow(str, sign)) {
            return if (sign == -1) {
                Int.MIN_VALUE
            } else {
                Int.MAX_VALUE
            }
        }

        // Change
        var result = 0
        for (i in str.indices) {
            result *= 10
            result += str[i].digitToInt() * sign
        }

        return result
    }

    private fun trimNonNumber(s: String): String {
        val regex = Regex("[^0-9]|^[ 0]*")
        return regex.replace(s, EMPTY)
    }

    private fun isOverflow(s: String, sign: Int): Boolean {
        val max = if (sign > 0) Int.MAX_VALUE.toString() else Int.MIN_VALUE.toString()
        return if (s.length > max.length) {
            true
        } else if (s.length < max.length) {
            false
        } else {
            for (i in max.indices) {
                if (s[i] > max[i]) {
                    return true
                }
            }
            false
        }
    }
}