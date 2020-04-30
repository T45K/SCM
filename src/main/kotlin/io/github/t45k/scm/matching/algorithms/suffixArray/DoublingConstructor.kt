package io.github.t45k.scm.matching.algorithms.suffixArray

import io.github.t45k.scm.entity.TokenSequence

class DoublingConstructor : ArrayConstructor {
    override fun construct(from: TokenSequence, querySize: Int): SuffixArray {
        val size: Int = from.size - querySize

        val sortedIndices: Array<Int> = Array(size) { it }
        var rank: Array<Int> = Array(size) { from[it] }
        var k = 1

        val compareByRank: Comparator<Int> = Comparator<Int> { i, j ->
            rank[i] - rank[j]
        }.thenComparing { i, j ->
            val iWithRank = if (i + k < size) rank[i + k] else -1
            val jWithRank = if (j + k < size) rank[j + k] else -1
            iWithRank - jWithRank
        }

        while (k <= size) {
            sortedIndices.sortWith(compareByRank)
            rank = updateRank(sortedIndices, compareByRank, size)
            k *= 2
        }

        return sortedIndices
            .map { it to from.subList(it, from.size) }
            .toList()
    }

    private fun updateRank(sortedIndices: Array<Int>, compareByRank: Comparator<Int>, size: Int): Array<Int> {
        val tmp: Array<Int> = Array(size) { 0 }
        tmp[sortedIndices[0]] = 0
        for (i in 1 until size) {
            tmp[sortedIndices[i]] = tmp[sortedIndices[i - 1]] + if (compareByRank.compare(sortedIndices[i - 1], sortedIndices[i]) >= 0) 0 else 1
        }
        return tmp
    }
}
