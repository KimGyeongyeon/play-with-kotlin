import kotlin.math.ceil
import kotlin.math.min

fun main() {
    parseInput().let { println(it) }
}

val SPACE = " "
fun parseInput(): Int {
    val n = readln().toInt() // n < 50. 크레인의 갯수
    val cranes = readln().split(SPACE).map { it.toInt() } // 크레인 무게 제한
    val m = readln().toInt() // m < 10^4. 박스 개수
    val boxes = readln().split(SPACE).map { it.toInt() } // 박스 무게
    return getMinTime(
        Port(
            cranes, boxes
        )
    )
}

fun getMinTime(port: Port): Int {
    port.assignBox()
    return port.craneCount.maxOf { it }
}

class Port(
    cranes: List<Int>,
    boxes: List<Int>
) {
    val craneDes = cranes.sortedDescending()
    val boxDes = boxes.sortedDescending()
    val craneCount = IntArray(cranes.size)
    val avg = ceil(boxes.size.toDouble() / cranes.size).toInt()

    fun assignBox() {
        var cn = 0
        for (box in boxDes) {
            if (cn > craneDes.size) {
                return // index 오류
            }
            if (craneDes[cn] < box) {
                return // 일어나면 안되는 상황
            }

            if (craneCount[cn] >= avg) {
                // 평균보다 많이 수행하는 상황이다.
                // 뒷 크레인에게 미룰 수 있으면 미룬다.
                if (cn < craneDes.size - 1 && craneDes[cn + 1] >= box) {
                    cn++
                    craneCount[cn]++
                } else {
                    // 앞 크레인 중 가장 한가한 크레인에게 넘긴다.
                    val lazyCrane = findLazyCraneIndex(cn)
                    craneCount[lazyCrane]++
                }

            } else {
                // box를 할당한다.
                craneCount[cn]++
            }
        }
    }

    fun findLazyCraneIndex(maxIndex: Int): Int {
        var index = 0
        var busy = Int.MAX_VALUE
        for (c in 0..min(maxIndex, craneDes.size)) {
            if (busy >= craneCount[c]) {
                busy = craneCount[c]
                index = c
            }
        }
        return index
    }
}