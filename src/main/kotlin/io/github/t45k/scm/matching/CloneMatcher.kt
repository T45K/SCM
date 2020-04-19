package io.github.t45k.scm.matching

import io.github.t45k.scm.entity.CodeFragment
import io.github.t45k.scm.entity.HashedCodeFragment
import io.github.t45k.scm.entity.TokenInfo
import io.github.t45k.scm.entity.to
import java.nio.file.Files
import java.nio.file.Path

class CloneMatcher(query: String) : ICloneMatcher {
    private val querySize: Int
    private val rollingHash: RollingHash
    private val hashedQuery: Int

    init {
        val tokenizedQuery: List<TokenInfo> = Tokenizer().tokenize(query)
        querySize = tokenizedQuery.size
        rollingHash = RollingHash(querySize)
        hashedQuery = rollingHash.calcInitial(tokenizedQuery.map { it.tokenNumber })
    }

    override fun search(paths: List<Path>) = paths.flatMap { search(it) }

    override fun search(path: Path): List<CodeFragment> {
        val contents: String = Files.readString(path)!!
        val tokenizedContents: List<TokenInfo> = Tokenizer().tokenize(contents)
        return hashContents(path, tokenizedContents)
            .filter { it.hash == hashedQuery }
            .map { it.codeFragment }
    }

    private fun hashContents(path: Path, tokenizedContents: List<TokenInfo>): List<HashedCodeFragment> {
        if (tokenizedContents.size < querySize) {
            return emptyList()
        }
        var hash: Int = rollingHash.calcInitial(tokenizedContents.take(querySize).map { it.tokenNumber })
        val initialHashedCodeFragment: HashedCodeFragment = hash to CodeFragment(path, tokenizedContents[0].lineNumber, tokenizedContents[querySize - 1].lineNumber)
        val hashedCodeFragments: List<HashedCodeFragment> = (querySize until tokenizedContents.size)
            .map { index ->
                val oldIndex: Int = index - querySize
                hash = rollingHash.calcWithBefore(hash.toLong(), tokenizedContents[oldIndex].tokenNumber, tokenizedContents[index].tokenNumber)
                hash to CodeFragment(path, tokenizedContents[oldIndex + 1].lineNumber, tokenizedContents[index].lineNumber)
            }
        return listOf(initialHashedCodeFragment).plus(hashedCodeFragments)
    }
}
