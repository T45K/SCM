package io.github.t45k.scm.matching.algorithms.suffixArray

import io.github.t45k.scm.entity.TokenSequence
import io.github.t45k.scm.entity.compareTo
import io.github.t45k.scm.matching.algorithms.StringSearchAlgorithm

class SuffixArraySearch(query: TokenSequence, private val arrayConstructor: ArrayConstructor = DoublingConstructor()) : StringSearchAlgorithm(query) {
    override fun search(from: List<Int>): List<Int> {
        val suffixArray: SuffixArray = arrayConstructor.construct(from, query.size)
        val startInclusive: Int = suffixArray.binarySearch(0 to query, Comparator { a, b -> if (a.tokenSequence >= b.tokenSequence) 1 else -1 }).let { if (it >= 0) it else it.inv() }
        val endExclusive: Int = suffixArray.binarySearch(0 to query, Comparator { a, b -> if (a.tokenSequence > b.tokenSequence) 1 else -1 }).let { if (it >= 0) it else it.inv() }
        return (startInclusive until endExclusive)
            .map { suffixArray[it].index }
            .toList()
    }
}
