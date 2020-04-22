package io.github.t45k.scm.matching

import io.github.t45k.scm.matching.RollingHash.HashedInt.Companion.MOD

class RollingHash(length: Int) : Calculator<Int> {
    private val memo: List<HashedInt>

    init {
        var tmp = 1.toHashable()
        memo = (1 until length)
            .map { it.toHashable() }
            .map {
                tmp *= BASE
                tmp
            }
            .reversed()
            .plus(1.toHashable())
    }

    companion object {
        const val BASE: Int = 1_020_544_910
    }

    override fun calcInitial(elements: List<Int>) =
        elements
            .map { it.toHashable() }
            .mapIndexed { index, value -> value * memo[index] }
            .reduce { acc, i -> (acc + i) }.intValue

    override fun calcWithBefore(before: Int, old: Int, new: Int) =
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
