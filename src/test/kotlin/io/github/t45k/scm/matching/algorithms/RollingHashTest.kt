package io.github.t45k.scm.matching.algorithms

import io.github.t45k.scm.matching.JDTTokenizer
import io.github.t45k.scm.matching.algorithms.rollingHash.RollingHash
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.Test
import kotlin.test.assertEquals

internal class RollingHashTest {
    @Test
    fun testSearch() {
        val query: List<Int> = JDTTokenizer().tokenize("a>b?a:b;")
            .map { it.tokenNumber }
        val from: List<Int> = JDTTokenizer().tokenize(Files.readString(Paths.get("./src/test/sample/Sample.java")))
            .map { it.tokenNumber }
        val searchedResult: List<Int> = RollingHash(query).search(from)
        assertEquals(2, searchedResult.size)
        assertEquals(18, searchedResult[0])
        assertEquals(42, searchedResult[1])
    }
}
