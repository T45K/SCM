package io.github.t45k.scm.matching

import io.github.t45k.scm.SCMConfiguration
import io.github.t45k.scm.entity.CodeFragment
import io.github.t45k.scm.entity.TokenInfo
import io.github.t45k.scm.matching.algorithms.Algorithm
import java.nio.file.Files
import java.nio.file.Path

class CloneMatcher(config: SCMConfiguration) : ICloneMatcher {
    private val query: List<TokenInfo> = config.query.joinToString(" ").let { JDTTokenizer().tokenize(it) }
    private val algorithm: Algorithm = Algorithm.create(config.algorithms, query.map { it.tokenNumber })

    override fun match(paths: List<Path>) = paths.flatMap { match(it) }

    override fun match(path: Path): List<CodeFragment> =
        Files.readString(path)!!
            .let { JDTTokenizer().tokenize(it) }
            .let { tokenSequence ->
                algorithm.search(tokenSequence.map { it.tokenNumber })
                    .map { CodeFragment(path, tokenSequence[it].lineNumber, tokenSequence[it + query.size - 1].lineNumber) }
            }
}
