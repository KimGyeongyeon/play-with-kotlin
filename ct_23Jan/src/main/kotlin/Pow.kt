import kotlin.math.absoluteValue

class Pow : Solution {
    override fun start() {
        println(
            "답 : ..."
        )
        println(
            myPow(
                2.0, -2147483648
            )
        )
    }

    fun myPow1(x: Double, n: Int): Double {
        if (x == 1.0 || x == 0.0) {
            return x
        } else if (x == -1.0) {
            return if (n % 2 == 1) x else 1.0
        }

        var count = n
        var result = 1.0

        // O(n) time이라 시간제한에 걸림
        while (count != 0) {
            if (n < 0) {
                count++
                result /= x
            } else {
                count--
                result *= x
            }
        }
        return result
    }

    // 부호랑 절대값을 따로 계산하자
    fun myPow(x: Double, n: Int): Double {
        return calculateSign(x < 0, n) * calculateAbsoluteValue(x.absoluteValue, n)
    }

    private fun calculateSign(isNegative: Boolean, n: Int): Double {
        return if (!isNegative) {
            1.0
        } else if (n % 2 == 0) {
            1.0
        } else {
            -1.0
        }
    }

    private fun calculateAbsoluteValue(absX: Double, n: Int): Double {
        // 예외처리
        if (absX == 1.0 || absX == 0.0) return absX
        if (n == 0) return 1.0

        // pre-processing : x^(2^i) 를 저장하는 테이블 만들기. O(log n) 소요
        // 이걸 다 저장하는 대신 재귀로 호출하면서 진행하면 공간 복잡도를 O(1)로 만들 수 있다.
        val powTable = createPowTable(absX, n)

        // n을 binary로 변환한 다음에 table에서 더해서 반환하면 됨
        val binaryN = n.absoluteValue.toString(2).reversed()
        var result = 1.0
        for (i in binaryN.indices) {
            if (binaryN[i] == '1') {
                result *= powTable[i]
            }
        }

        return result
    }

    fun createPowTable(absX: Double, n: Int): ArrayList<Double> {
        val powTable = arrayListOf<Double>()
        var nCopy = n
        var index = 1
        if (n < 0){
            powTable.add(1/absX)
        } else {
            powTable.add(absX)
        }
        while (nCopy != 0){
            val square = powTable[index - 1] * powTable[index - 1]
            powTable.add(square)
            nCopy /= 2
            index++
        }
        return powTable
    }
}