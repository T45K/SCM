package io.github.t45k.scm.matching.algorithms.suffixArray

import io.github.t45k.scm.entity.TokenSequence

class DoublingConstructor : ArrayConstructor {
    override fun construct(from: TokenSequence, querySize: Int): SuffixArray {
        val size: Int = from.size - querySize

        val sortedIndices: Array<Int> = Array(size) { it }
        var rank: Array<Int> = Array(size) { from[it] }
        var k = 1

        val compareWithRank: Comparator<Int> = Comparator { i, j ->
            if (rank[i] != rank[j]) {
                rank[i] - rank[j]
            } else {
                val iWithRank = if (i + k < size) rank[i + k] else -1
                val jWithRank = if (j + k < size) rank[j + k] else -1
                iWithRank - jWithRank
            }
        }

        while (k <= size) {
            sortedIndices.sortWith(compareWithRank)
            val tmp: Array<Int> = Array(size) { it }
            tmp[sortedIndices[0]] = 0
            for (i in 1 until size) {
                tmp[sortedIndices[i]] = tmp[sortedIndices[i - 1]] + if (compareWithRank.compare(sortedIndices[i - 1], sortedIndices[i]) >= 0) 0 else 1
            }
            rank = tmp
            k *= 2
        }

        return sortedIndices.map { it to from.subList(it, from.size) }
            .toList()
    }
}
