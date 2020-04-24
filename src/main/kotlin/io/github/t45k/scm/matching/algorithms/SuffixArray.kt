package io.github.t45k.scm.matching.algorithms

class SuffixArray(private val query: List<Int>) : Algorithm(query) {
    override fun search(from: List<Int>): List<Int> {
        val table: List<Pair<Int, List<Int>>> = constructSuffixArray(from)
        val startInclusive: Int = table.binarySearch(0 to query, Comparator { a, b -> if (a.second >= b.second) 1 else -1 }).let { if (it >= 0) it else it.inv() }
        val endExclusive: Int = table.binarySearch(0 to query, Comparator { a, b -> if (a.second > b.second) 1 else -1 }).let { if (it >= 0) it else it.inv() }
        return (startInclusive until endExclusive)
            .map { table[it].first }
            .toList()
    }

    private fun constructSuffixArray(from: List<Int>): List<Pair<Int, List<Int>>> =
        (0 until from.size - query.size)
            .mapIndexed { index: Int, value: Int -> index to from.subList(value, from.size) }
            .sortedWith(Comparator { a, b -> a.second.compareTo(b.second) })

    private operator fun List<Int>.compareTo(list: List<Int>): Int {
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
}
