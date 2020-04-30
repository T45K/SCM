package io.github.t45k.scm.matching.algorithms.suffixArray

import io.github.t45k.scm.entity.TokenSequence
import io.github.t45k.scm.entity.compareTo

class NaiveConstructor : ArrayConstructor {
    override fun construct(from: TokenSequence, querySize: Int): SuffixArray =
        (0 until from.size - querySize)
            .mapIndexed { index: Int, value: Int -> index to from.subList(value, from.size) }
            .sortedWith(Comparator { a, b -> a.tokenSequence.compareTo(b.tokenSequence) })
}
