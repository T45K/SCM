package io.github.t45k.scm.matching

import io.github.t45k.scm.entity.TokenInfo
import io.github.t45k.scm.entity.to
import org.eclipse.jdt.core.ToolFactory
import org.eclipse.jdt.core.compiler.IScanner
import org.eclipse.jdt.core.compiler.ITerminalSymbols

class Tokenizer : ITokenizer {
    override fun tokenize(contents: String): List<TokenInfo> {
        val scanner: IScanner = ToolFactory
            .createScanner(false, false, true, true)
            .setSource(contents)

        return (1..contents.length).asSequence()
            .map { scanner.nextToken.toLong() to scanner.getLineNumber(scanner.currentTokenStartPosition) }
            .takeWhile { it.tokenNumber.toInt() != ITerminalSymbols.TokenNameEOF }
            .map { normalizeIfNeeded(it) }
            .toList()
    }

    private fun normalizeIfNeeded(tokenInfo: TokenInfo): TokenInfo {
        @Suppress("DEPRECATION")
        return when (tokenInfo.tokenNumber.toInt()) {
            ITerminalSymbols.TokenNameIdentifier -> 1000L to tokenInfo.lineNumber
            ITerminalSymbols.TokenNameCharacterLiteral,
            ITerminalSymbols.TokenNameDoubleLiteral,
            ITerminalSymbols.TokenNameFloatingPointLiteral,
            ITerminalSymbols.TokenNameIntegerLiteral,
            ITerminalSymbols.TokenNameLongLiteral,
            ITerminalSymbols.TokenNameStringLiteral -> 1001L to tokenInfo.lineNumber
            else -> tokenInfo
        }
    }

    private fun IScanner.setSource(source: String): IScanner {
        this.source = source.toCharArray()
        return this
    }
}
