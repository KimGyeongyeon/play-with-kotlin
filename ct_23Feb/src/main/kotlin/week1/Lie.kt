package week1

import Solution

class Lie : Solution {
    override fun start() {
        val res = parseInput { truth, arr ->
            solution(truth, arr)
        }
        println(res)
    }

    fun parseInput(func: (HashSet<Int>, Array<HashSet<Int>>) -> Int): Int {
        // 1st line
        val numbers = readLine()?.split(" ") ?: return -1
        if (numbers.size != 2) return -2

        val totalMember = numbers[0]
        val partyNumber = numbers[1].toIntOrNull() ?: return -3

        // 2nd line
        val truthInfo = readLine()?.split(" ") ?: return -4
        val initialTruthMembers = hashSetOf<Int>()
        for (i in 1..truthInfo[0].toInt()) {
            truthInfo[i].toIntOrNull()?.let {
                initialTruthMembers.add(it)
            }
        }

        // else
        val parties = Array<HashSet<Int>>(partyNumber) { hashSetOf() }
        for (i in 0 until partyNumber) {
            val input = readLine()?.split(" ") ?: return -5

            for (j in 1..input[0].toInt()) {
                input[j].toIntOrNull()?.let {
                    parties[i].add(it)
                }
            }
        }

        return func(initialTruthMembers, parties)
    }

    // set은 contains 연산을 constant time에 수행하기 때문에 유리하다.
    fun solution(initialTruthMembers: HashSet<Int>, parties: Array<HashSet<Int>>): Int {
        // 안들키고 과장할 수 있는 파티의 최댓값
        val truthMembers: HashSet<Int> = HashSet(initialTruthMembers)
        for (i in parties.indices) {
            val party = parties[i]
            if (party.any { it -> truthMembers.contains(it) }) {
                truthMembers.addAll(party)
            }
        }

        return parties.count {
                party -> party.intersect(truthMembers).isEmpty()
        }
    }
}