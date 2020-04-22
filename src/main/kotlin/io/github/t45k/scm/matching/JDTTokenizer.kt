package io.github.t45k.scm.matching

import io.github.t45k.scm.entity.TokenInfo
import io.github.t45k.scm.entity.to
import org.eclipse.jdt.core.ToolFactory
import org.eclipse.jdt.core.compiler.IScanner
import org.eclipse.jdt.core.compiler.ITerminalSymbols.TokenNameCharacterLiteral
import org.eclipse.jdt.core.compiler.ITerminalSymbols.TokenNameDoubleLiteral
import org.eclipse.jdt.core.compiler.ITerminalSymbols.TokenNameEOF
import org.eclipse.jdt.core.compiler.ITerminalSymbols.TokenNameFloatingPointLiteral
import org.eclipse.jdt.core.compiler.ITerminalSymbols.TokenNameIdentifier
import org.eclipse.jdt.core.compiler.ITerminalSymbols.TokenNameIntegerLiteral
import org.eclipse.jdt.core.compiler.ITerminalSymbols.TokenNameLongLiteral
import org.eclipse.jdt.core.compiler.ITerminalSymbols.TokenNameStringLiteral

class JDTTokenizer : Tokenizer {
    override fun tokenize(contents: String): List<TokenInfo> {
        val scanner: IScanner = ToolFactory
            .createScanner(false, false, true, true)
            .setSource(contents)

        return (1..contents.length).asSequence()
            .map { scanner.nextToken to scanner.getLineNumber(scanner.currentTokenStartPosition) }
            .takeWhile { it.tokenNumber != TokenNameEOF }
            .map { normalizeIfNeeded(it) }
            .toList()
    }

    private fun normalizeIfNeeded(tokenInfo: TokenInfo): TokenInfo {
        @Suppress("DEPRECATION")
        return when (tokenInfo.tokenNumber) {
            TokenNameIdentifier -> 1000 to tokenInfo.lineNumber
            TokenNameCharacterLiteral,
            TokenNameDoubleLiteral,
            TokenNameFloatingPointLiteral,
            TokenNameIntegerLiteral,
            TokenNameLongLiteral,
            TokenNameStringLiteral -> 1001 to tokenInfo.lineNumber
            else -> tokenInfo
        }
    }

    private fun IScanner.setSource(source: String): IScanner {
        this.source = source.toCharArray()
        return this
    }
}
