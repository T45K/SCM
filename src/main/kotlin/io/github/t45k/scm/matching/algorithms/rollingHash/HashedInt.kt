package io.github.t45k.scm.matching.algorithms.rollingHash

import io.github.t45k.scm.matching.algorithms.rollingHash.HashedInt.Companion.MOD

data class HashedInt(val value: Int) {
    companion object {
        const val MOD: Int = 2_147_483_647
    }

    operator fun times(a: Int) = (this.value.toLong() * a.toLong()).toHashable()
    operator fun times(a: HashedInt) = this * a.value
    operator fun plus(a: Int) = ((this.value.toLong() + a.toLong())).toHashable()
    operator fun plus(a: HashedInt) = this + a.value
}

operator fun Int.minus(a: HashedInt) = (this.toLong() + MOD.toLong() - a.value.toLong()).toHashable()
operator fun Int.times(a: HashedInt) = a * this

fun Int.toHashable() = HashedInt(this)
fun Long.toHashable() = (this % MOD.toLong()).toInt().toHashable()
