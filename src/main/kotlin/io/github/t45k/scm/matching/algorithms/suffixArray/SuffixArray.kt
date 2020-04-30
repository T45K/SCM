package io.github.t45k.scm.matching.algorithms.suffixArray

import io.github.t45k.scm.entity.TokenSequence

typealias SuffixArray = List<IndexedTokenSequence>

data class IndexedTokenSequence(val index: Int, val tokenSequence: TokenSequence)

infix fun Int.to(tokenSequence: TokenSequence): IndexedTokenSequence = IndexedTokenSequence(this, tokenSequence)
