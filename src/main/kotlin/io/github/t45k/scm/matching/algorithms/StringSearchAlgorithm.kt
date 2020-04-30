package io.github.t45k.scm.matching.algorithms

import io.github.t45k.scm.SCMConfiguration.Algorithm
import io.github.t45k.scm.SCMConfiguration.Algorithm.ROLLING_HASH
import io.github.t45k.scm.SCMConfiguration.Algorithm.SUFFIX_ARRAY
import io.github.t45k.scm.SCMConfiguration.Algorithm.SUFFIX_ARRAY_DOUBLING
import io.github.t45k.scm.SCMConfiguration.Algorithm.SUFFIX_ARRAY_NAIVE
import io.github.t45k.scm.entity.TokenSequence
import io.github.t45k.scm.matching.algorithms.rollingHash.RollingHash
import io.github.t45k.scm.matching.algorithms.suffixArray.NaiveConstructor
import io.github.t45k.scm.matching.algorithms.suffixArray.SuffixArraySearch

abstract class StringSearchAlgorithm(protected val query: TokenSequence) {
    abstract fun search(from: TokenSequence): List<Int>

    companion object {
        fun create(algorithm: Algorithm, query: List<Int>): StringSearchAlgorithm =
            when (algorithm) {
                ROLLING_HASH -> RollingHash(query)
                SUFFIX_ARRAY_NAIVE -> SuffixArraySearch(query, NaiveConstructor())
                SUFFIX_ARRAY, SUFFIX_ARRAY_DOUBLING -> SuffixArraySearch(query)
            }
    }
}
