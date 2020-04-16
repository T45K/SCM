package io.github.t45k.scm.matching

import io.github.t45k.scm.entity.TokenInfo
import io.reactivex.Single

interface ITokenizer {
    fun tokenize(contents: String): Single<List<TokenInfo>>
}
