package week1

import Solution
import getArrayWithLength

class Lie : Solution {
    override fun start() {
        val res = parseInput { n, truth, party ->
            solution2(n, truth, party)
        }
        println(res)
    }

    fun parseInput(func: (Int, IntArray, Array<HashSet<Int>>) -> Int): Int {
        // 1st line
        val numbers = readLine()?.split(" ") ?: return -1
        if (numbers.size != 2) return -2

        val totalMember = numbers[0].toIntOrNull() ?: return -3
        val partyNumber = numbers[1].toIntOrNull() ?: return -3

        // 2nd line
        val initialTruthMembers = getArrayWithLength()

        // Party info
        val parties = Array<HashSet<Int>>(partyNumber) { hashSetOf() }
        for (i in 0 until partyNumber) {
            val input = readLine()?.split(" ") ?: return -5

            for (j in 1..input[0].toInt()) {
                input[j].toIntOrNull()?.let {
                    parties[i].add(it)
                }
            }
        }

        return func(totalMember, initialTruthMembers, parties)
    }

    fun solution1(n: Int, initialTruthMembers: IntArray, parties: Array<HashSet<Int>>): Int {
        // Set을 사용한 방식은 두 다리를 건너서 진실을 아는 경우 적용할 수 없다.
        val truthMembers: HashSet<Int> = initialTruthMembers.toHashSet()
        for (i in parties.indices) {
            val party = parties[i]
            if (party.any { it -> truthMembers.contains(it) }) {
                truthMembers.addAll(party)
            }
        }
        return parties.count { party ->
            party.intersect(truthMembers).isEmpty()
        }
    }

    /* 27.484MB, 204ms */
    fun solution2(n: Int, initialTruthMembers: IntArray, parties: Array<HashSet<Int>>): Int {
        // BFS를 사용하기로 했다.

        // 파티를 그래프로 나타낸다.
        val relationship = Array<HashSet<Int>>(n + 1) { hashSetOf() }
        for (i in parties.indices) {
            val party = parties[i]
            for (member in party.toIntArray()) {
                relationship[member].addAll(party)
            }
        }

        // 진실을 아는 사람을 모조리 찾아낸다.
        val queue = ArrayDeque<Int>()
        val visited = HashSet<Int>()

        for (member in initialTruthMembers) {
            queue.add(member)

            while (queue.isNotEmpty()) {
                val curr = queue.removeFirst()
                if (!visited.contains(curr)) {
                    visited.add(curr)
                    queue.addAll(relationship[curr])
                }
            }
        }

        return parties.count { party ->
            party.intersect(visited).isEmpty()
        }
    }


}