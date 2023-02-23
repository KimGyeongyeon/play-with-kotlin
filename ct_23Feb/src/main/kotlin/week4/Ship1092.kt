package week4

import Solution
import kotlin.math.ceil

class Ship1092: Solution {
    override fun start() {
        println("sdf")
        getMinTime(
            listOf(10, 5, 1),
            listOf(2, 2, 2, 2, 2)
        )
    }

    val SPACE = " "
    fun parseInput() {
        val n = readln().toInt() // n < 50. 크레인의 갯수
        val cranes = readln().split(SPACE).map { it.toInt() } // 크레인 무게 제한
        val m = readln().toInt() // m < 10^4. 박스 개수
        val boxes = readln().split(SPACE).map { it.toInt() } // 박스 무게
    }

    fun getMinTime(cranes: List<Int>, boxes: List<Int>): Int {
        // 모든 박스를 배로 옮기는데에 드는 시간의 최솟값
        // 크레인은 1분에 박스 하나를 실을 수 있다.
        // 크레인은 동시에 움직인다.
        // 그럼 최대한 크레인을 많이 활용할 수 있게 해야겠네
        // 박스 / 크레인 만큼씩 할당되는게 베스트!
        val avg = ceil(boxes.size.toDouble() / cranes.size).toInt()
        val cranesDes = cranes.sortedDescending()
        val count = IntArray(cranes.size)
        val boxDes = boxes.sortedDescending()

        // 일단 각 크레인마다 avg만큼 박스를 할당한다.
        var i = 0
        for (c in cranesDes.indices) {
            if (cranesDes[c] >= boxDes[i] && count[c] < avg) {
                count[c]++
                i++
            }
        }

        // 남은 박스를 처리한다.
        var crane = 0
        while (i < boxDes.size) {
            if (cranesDes[crane] >= boxDes[i]) {
                count[crane]++
                i++
                crane++
            } else {
                // box가 무거워서 앞쪽 크레인에게 맡겨야한다.
                crane--
            }

            // 인덱스 조정
            if (crane >= cranesDes.size) {
                crane = 0
            }
        }

        return count.maxOf { it }
    }
}