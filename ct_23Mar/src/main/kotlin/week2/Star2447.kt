package week2

class Star2447 {
    fun main() {
        val n = readln().toInt()
        println(
            star(n)
        )
    }

    fun star(n: Int): String {
        return if (n == 1) {
            "*"
        } else {
            val lines = star(n / 3).split("\n")
            var end = ""
            var middle = ""
            for (line in lines) {
                end += (line * 3 + "\n")
                middle += "$line${" " * line.length}$line\n"
            }
            "$end$middle${end.trimLast()}"
        }
    }

    operator fun String.times(n: Int): String {
        var result = ""
        repeat(n) {
            result += this
        }
        return result
    }

    fun String.trimLast(): String {
        return slice(0 until length - 1)
    }
}