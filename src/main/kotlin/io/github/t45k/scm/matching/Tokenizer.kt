package io.github.t45k.scm.matching

import io.reactivex.Observable
import io.reactivex.Single
import org.eclipse.jdt.core.ToolFactory
import org.eclipse.jdt.core.compiler.IScanner
import org.eclipse.jdt.core.compiler.ITerminalSymbols

class Tokenizer {
    fun tokenize(content: String): Single<List<Int>> =
            Observable.just(content)
                    .map {
                        ToolFactory
                                .createScanner(false, false, true, false)
                                ?.setSource(it)
                                ?: emptyScanner()
                    }
                    .flatMap { Observable.just(it.nextToken) }
                    .takeWhile { it != ITerminalSymbols.TokenNameEOF }
                    .map { normalizeIfNeeded(it) }
                    .toList()

    private fun normalizeIfNeeded(tokenNumber: Int): Int {
        @Suppress("DEPRECATION")
        return when (tokenNumber) {
            ITerminalSymbols.TokenNameIdentifier -> 1000
            ITerminalSymbols.TokenNameCharacterLiteral,
            ITerminalSymbols.TokenNameDoubleLiteral,
            ITerminalSymbols.TokenNameFloatingPointLiteral,
            ITerminalSymbols.TokenNameIntegerLiteral,
            ITerminalSymbols.TokenNameLongLiteral,
            ITerminalSymbols.TokenNameStringLiteral -> 1001
            else -> tokenNumber
        }
    }

    private fun IScanner.setSource(source: String): IScanner {
        this.source = source.toCharArray()
        return this
    }

    private fun emptyScanner(): IScanner =
            ToolFactory.createScanner(false, false, true, false).setSource("")

}
