package io.github.t45k.scm.matching

import io.github.t45k.scm.entity.TokenInfo
import org.eclipse.jdt.core.ToolFactory
import org.eclipse.jdt.core.compiler.ITerminalSymbols.TokenNameEOF

class JDTTokenizer : Tokenizer {
    override fun tokenize(contents: String): List<TokenInfo> =
        ToolFactory
            .createScanner(false, false, true, true)
            .also { it.source = contents.toCharArray() }
            .let { scanner ->
                generateSequence { 0 }
                    .map { TokenInfo(scanner.nextToken, it, scanner.getLineNumber(scanner.currentTokenStartPosition)) }
                    .takeWhile { it.tokenNumber != TokenNameEOF }
                    .map { it.normalize() }
                    .toList()
            }
}
