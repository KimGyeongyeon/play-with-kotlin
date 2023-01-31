class PerfectSquare : Solution {
    override fun start() {
        val ans = isPerfectSquare(2000000000)
        println(ans)
    }

    private val SQRT_OF_MAX_INT = Math.sqrt(Int.MAX_VALUE.toDouble()).toInt()

    fun isPerfectSquare(num: Int): Boolean {
        if (num == 1) {
            return true
        }

        var start = 0
        var end = Math.min(num, SQRT_OF_MAX_INT)

        while (start < end){
            // 무한루프 돌고있음;; 그냥 템플릿 신경쓰지말고 알아서짜자 템플릿 베끼다가 이사단난다
            val mid = start + (end - start + 1) / 2
            val square = mid * mid

            if (num == square) {
                return true
            } else if(num > square) {
                // 오른쪽
                start = mid - 1
            } else {
                // 왼쪽 탐색
                end = mid + 1
            }
        }
        return false
    }
}