package io.github.t45k.scm.matching

interface Calculator<T> {
    fun calcInitial(elements: List<T>): Int
    fun calcWithBefore(before: T, old: T, new: T): Int
}
