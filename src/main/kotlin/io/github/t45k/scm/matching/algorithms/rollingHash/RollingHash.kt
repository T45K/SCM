package io.github.t45k.scm.matching.algorithms.rollingHash

import io.github.t45k.scm.entity.TokenSequence
import io.github.t45k.scm.matching.algorithms.Algorithm


class RollingHash(query: TokenSequence) : Algorithm(query) {
    private val querySize: Int = query.size

    private val memo: List<HashedInt>
    private val hashedQuery: Int

    init {
        var tmp = 1.toHashable()
        memo = (1 until querySize)
            .map {
                tmp *= BASE
                tmp
            }
            .reversed()
            .plus(1.toHashable())

        hashedQuery = calcInitial(query)
    }

    companion object {
        const val BASE: Int = 1_020_544_910
    }

    override fun search(from: TokenSequence): List<Int> {
        if (from.size < querySize) {
            return emptyList()
        }

        var rollingHash: Int = calcInitial(from.take(querySize))
        return listOf(rollingHash).plus(
            (querySize until from.size)
                .map { index ->
                    rollingHash = calcWithBefore(rollingHash, from[index - querySize], from[index])
                    rollingHash
                })
            .mapIndexed { index, hash -> index to hash }
            .filter { it.second == hashedQuery }
            .map { it.first }
    }

    private fun calcInitial(elements: TokenSequence) =
        elements
            .map { it.toHashable() }
            .mapIndexed { index, value -> value * memo[index] }
            .reduce { acc, i -> (acc + i) }.value

    private fun calcWithBefore(before: Int, old: Int, new: Int) =
        ((before - old * memo[0]) * BASE + new).value
}
