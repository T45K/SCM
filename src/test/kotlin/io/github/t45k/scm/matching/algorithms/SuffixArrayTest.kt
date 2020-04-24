package io.github.t45k.scm.matching.algorithms

import io.github.t45k.scm.matching.JDTTokenizer
import org.junit.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.assertEquals


internal class SuffixArrayTest {
    @Test
    fun testSearch() {
        val query: List<Int> = JDTTokenizer().tokenize("a>b?a:b;")
            .map { it.tokenNumber }
        val from: List<Int> = JDTTokenizer().tokenize(Files.readString(Paths.get("./src/test/sample/Sample.java")))
            .map { it.tokenNumber }
        val searchedResult: List<Int> = SuffixArray(query).search(from)
        assertEquals(2, searchedResult.size)
        assertEquals(42, searchedResult[0])
        assertEquals(18, searchedResult[1])
    }
}
