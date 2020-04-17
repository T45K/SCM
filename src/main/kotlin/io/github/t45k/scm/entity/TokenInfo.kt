package io.github.t45k.scm.entity

data class TokenInfo(val tokenNumber: Long, val lineNumber: Int)

infix fun Long.to(lineNumber: Int): TokenInfo = TokenInfo(this, lineNumber)
