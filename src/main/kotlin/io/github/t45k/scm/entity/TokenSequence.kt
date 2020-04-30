package io.github.t45k.scm.entity

typealias TokenSequence = List<Int>

operator fun TokenSequence.compareTo(list: List<Int>): Int {
    for (i in 0 until this.size.coerceAtMost(list.size)) {
        if (this[i] > list[i]) {
            return 1
        }
        if (this[i] < list[i]) {
            return -1
        }
    }

    return 0
}
