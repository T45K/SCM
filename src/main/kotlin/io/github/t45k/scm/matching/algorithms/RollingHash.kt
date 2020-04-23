package io.github.t45k.scm.matching.algorithms

import io.github.t45k.scm.matching.algorithms.RollingHash.HashedInt.Companion.MOD

class RollingHash(query: List<Int>) : Algorithm(query) {
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

    override fun search(from: List<Int>): List<Int> {
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

    private fun calcInitial(elements: List<Int>) =
        elements
            .map { it.toHashable() }
            .mapIndexed { index, value -> value * memo[index] }
            .reduce { acc, i -> (acc + i) }.intValue

    private fun calcWithBefore(before: Int, old: Int, new: Int) =
        ((before - old * memo[0]) * BASE + new).intValue

    internal data class HashedInt(val intValue: Int) {
        companion object {
            const val MOD: Int = 2_147_483_647
        }
    }

    private fun Int.toHashable() = HashedInt(this)
    private fun Long.toHashable() = (this % MOD.toLong()).toInt().toHashable()
    private operator fun HashedInt.times(a: Int) = (this.intValue.toLong() * a.toLong()).toHashable()
    private operator fun HashedInt.times(a: HashedInt) = this * a.intValue
    private operator fun HashedInt.plus(a: Int) = ((this.intValue.toLong() + a.toLong())).toHashable()
    private operator fun HashedInt.plus(a: HashedInt) = this + a.intValue
    private operator fun Int.times(a: HashedInt) = a * this
    private operator fun Int.minus(a: HashedInt) = (this.toLong() + MOD.toLong() - a.intValue.toLong()).toHashable()
}
