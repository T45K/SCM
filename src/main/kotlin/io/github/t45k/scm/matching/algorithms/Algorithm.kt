package io.github.t45k.scm.matching.algorithms

import io.github.t45k.scm.SCMConfiguration.Algorithms
import io.github.t45k.scm.SCMConfiguration.Algorithms.ROLLING_HASH
import io.github.t45k.scm.SCMConfiguration.Algorithms.SUFFIX_ARRAY
import io.github.t45k.scm.entity.TokenSequence
import io.github.t45k.scm.matching.algorithms.rollingHash.RollingHash
import io.github.t45k.scm.matching.algorithms.suffixArray.SuffixArraySearch

abstract class Algorithm(protected val query: TokenSequence) {
    abstract fun search(from: TokenSequence): List<Int>

    companion object {
        fun create(algorithms: Algorithms, query: List<Int>): Algorithm =
            when (algorithms) {
                ROLLING_HASH -> RollingHash(query)
                SUFFIX_ARRAY -> SuffixArraySearch(query)
            }
    }
}
