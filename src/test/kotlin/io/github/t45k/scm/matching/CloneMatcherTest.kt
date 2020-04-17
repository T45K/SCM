package io.github.t45k.scm.matching

import io.github.t45k.scm.entity.HashedCodeFragment
import java.nio.file.Paths
import kotlin.test.Test
import kotlin.test.assertEquals

internal class CloneMatcherTest {
    @Test
    fun testSearch() {
        val query = "a>b?a:b;"
        val cloneMatcher = CloneMatcher(query)
        val searchResult: List<HashedCodeFragment> = cloneMatcher.search(Paths.get("./src/test/sample/Sample.java"))
        assertEquals(2, searchResult.size)
        println(searchResult)
    }

    @Test
    fun testSearchLiteral() {
        val query = "var=0"
        val cloneMatcher = CloneMatcher(query)
        val searchResult: List<HashedCodeFragment> = cloneMatcher.search(Paths.get("./src/test/sample/Sample.java"))
        assertEquals(7, searchResult.size)
        println(searchResult)
    }
}
