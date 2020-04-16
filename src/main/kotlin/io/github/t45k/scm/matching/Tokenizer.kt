package io.github.t45k.scm.matching

import io.github.t45k.scm.entity.TokenInformation
import io.github.t45k.scm.entity.to
import io.reactivex.Observable
import io.reactivex.Single
import org.eclipse.jdt.core.ToolFactory
import org.eclipse.jdt.core.compiler.IScanner
import org.eclipse.jdt.core.compiler.ITerminalSymbols

class Tokenizer {
    fun tokenize(content: String): Single<List<TokenInformation>> =
        Observable.just(content)
            .map {
                ToolFactory
                    .createScanner(false, false, true, false)
                    .setSource(it)
            }
            .flatMap { Observable.just(it.nextToken to it.getLineNumber(it.currentTokenStartPosition)) }
            .takeWhile { it.tokenNumber != ITerminalSymbols.TokenNameEOF }
            .map { normalizeIfNeeded(it) }
            .toList()

    private fun normalizeIfNeeded(tokenInformation: TokenInformation): TokenInformation {
        @Suppress("DEPRECATION")
        return when (tokenInformation.tokenNumber) {
            ITerminalSymbols.TokenNameIdentifier -> 1000 to tokenInformation.lineNumber
            ITerminalSymbols.TokenNameCharacterLiteral,
            ITerminalSymbols.TokenNameDoubleLiteral,
            ITerminalSymbols.TokenNameFloatingPointLiteral,
            ITerminalSymbols.TokenNameIntegerLiteral,
            ITerminalSymbols.TokenNameLongLiteral,
            ITerminalSymbols.TokenNameStringLiteral -> 1001 to tokenInformation.lineNumber
            else -> tokenInformation
        }
    }

    private fun IScanner.setSource(source: String): IScanner {
        this.source = source.toCharArray()
        return this
    }
}
