package io.github.t45k.scm.entity

import org.eclipse.jdt.core.compiler.ITerminalSymbols.TokenNameCharacterLiteral
import org.eclipse.jdt.core.compiler.ITerminalSymbols.TokenNameDoubleLiteral
import org.eclipse.jdt.core.compiler.ITerminalSymbols.TokenNameFloatingPointLiteral
import org.eclipse.jdt.core.compiler.ITerminalSymbols.TokenNameIdentifier
import org.eclipse.jdt.core.compiler.ITerminalSymbols.TokenNameIntegerLiteral
import org.eclipse.jdt.core.compiler.ITerminalSymbols.TokenNameLongLiteral
import org.eclipse.jdt.core.compiler.ITerminalSymbols.TokenNameStringLiteral

data class TokenInfo(val tokenNumber: Int, val index: Int, val lineNumber: Int) {
    companion object {
        private const val NormalizedIdentifier = 1000
        private const val NormalizedLiteral = 1001
    }

    @Suppress("DEPRECATION")
    fun normalize() =
        when (this.tokenNumber) {
            TokenNameIdentifier -> TokenInfo(NormalizedIdentifier, index, lineNumber)
            TokenNameCharacterLiteral,
            TokenNameDoubleLiteral,
            TokenNameFloatingPointLiteral,
            TokenNameIntegerLiteral,
            TokenNameLongLiteral,
            TokenNameStringLiteral -> TokenInfo(NormalizedLiteral, index, lineNumber)
            else -> this
        }
}
