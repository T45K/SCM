package io.github.t45k.scm.matching

import io.github.t45k.scm.entity.TokenInfo

interface ITokenizer {
    fun tokenize(contents: String): List<TokenInfo>
}
