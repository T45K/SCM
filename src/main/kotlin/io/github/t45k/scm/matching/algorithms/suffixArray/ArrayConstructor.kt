package io.github.t45k.scm.matching.algorithms.suffixArray

import io.github.t45k.scm.entity.TokenSequence

interface ArrayConstructor {
    fun construct(from: TokenSequence,querySize:Int): SuffixArray
}
