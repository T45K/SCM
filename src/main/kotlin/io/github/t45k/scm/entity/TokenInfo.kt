package io.github.t45k.scm.entity

data class TokenInfo(val tokenNumber: Int, val lineNumber: Int)

infix fun Int.to(lineNumber: Int): TokenInfo = TokenInfo(this, lineNumber)
