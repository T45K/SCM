package io.github.t45k.scm.matching

class RollingHash(length: Int) : Calculator<Long> {
    private val memo: List<Long>

    init {
        var tmp = 1L
        memo = (1 until length)
            .map {
                tmp = tmp * BASE % MOD
                tmp
            }
            .reversed()
            .plus(1)
    }

    companion object {
        const val MOD: Long = 2_147_483_647
        const val BASE: Long = 1_020_544_910
    }

    override fun calcInitial(elements: List<Long>) =
        elements
            .mapIndexed { index, value -> value * memo[index] % MOD }
            .reduce { acc, i -> (acc + i) % MOD }.toInt()

    override fun calcWithBefore(before: Long, old: Long, new: Long) =
        (((before - old * memo[0] % MOD + MOD) % MOD * BASE + new) % MOD).toInt()
}
