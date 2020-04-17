package io.github.t45k.scm.matching

interface Calculator<T> {
    fun calcInitial(elements: List<T>): Long
    fun calcWithBefore(before: T, old: T, new: T): Long
}
