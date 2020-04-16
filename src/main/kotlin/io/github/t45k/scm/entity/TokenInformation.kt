package io.github.t45k.scm.entity

data class TokenInformation(val tokenNumber: Int, val lineNumber: Int)

infix fun Int.to(lineNumber: Int): TokenInformation = TokenInformation(this, lineNumber)
