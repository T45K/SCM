package io.github.t45k.scm.matching.algorithms

abstract class Algorithm(query: List<Int>) {
    abstract fun search(from: List<Int>): List<Int>
}
