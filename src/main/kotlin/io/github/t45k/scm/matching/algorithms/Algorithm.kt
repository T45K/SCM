package io.github.t45k.scm.matching.algorithms

import io.github.t45k.scm.SCMConfiguration.Algorithms
import io.github.t45k.scm.SCMConfiguration.Algorithms.ROLLING_HASH
import io.github.t45k.scm.SCMConfiguration.Algorithms.SUFFIX_ARRAY

abstract class Algorithm(query: List<Int>) {
    abstract fun search(from: List<Int>): List<Int>

    companion object {
        fun create(algorithms: Algorithms, query: List<Int>): Algorithm =
            when (algorithms) {
                ROLLING_HASH -> RollingHash(query)
                SUFFIX_ARRAY -> SuffixArray(query)
            }
    }
}
