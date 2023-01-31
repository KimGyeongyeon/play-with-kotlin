class Fizz : Solution {
    override fun start() {
        val n = 15
        println(fizzBuzz_my(n))
    }

    fun fizzBuzz_my(n: Int): List<String> {
        val result = MutableList(n) { "" }
        for (num in 1..n) {
            result[num - 1] = if (num % 3 == 0 && num % 5 == 0) {
                "FizzBuzz"
            } else if (num % 3 == 0) {
                "Fizz"
            } else if (num % 5 == 0) {
                "Buzz"
            } else {
                num.toString()
            }
        }
        return result
    }

    fun fizzBuzz_memory(n: Int): List<String> {
        val result = mutableListOf<String>()
        for (i in 1..n) {
            when {
                i % 3 == 0 && i % 5 == 0 -> result.add("FizzBuzz")
                i % 3 == 0 -> result.add("Fizz")
                i % 5 == 0 -> result.add("Buzz")
                else -> result.add(i.toString())
            }
        }
        return result
    }
}