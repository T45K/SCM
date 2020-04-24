package io.github.t45k.scm.matching

import io.github.t45k.scm.SCMConfiguration
import io.github.t45k.scm.entity.CodeFragment
import java.nio.file.Paths
import kotlin.test.Test
import kotlin.test.assertEquals

internal class CloneMatcherTest {
    @Test
    fun testMatch() {
        val query = "a>b?a:b;"
        val config = SCMConfiguration().also { it.query = arrayOf(query) }
        val cloneMatcher = CloneMatcher(config)
        val matchingResult: List<CodeFragment> = cloneMatcher.match(Paths.get("./src/test/sample/Sample.java"))
        assertEquals(2, matchingResult.size)
        println(matchingResult)
    }

    @Test
    fun testMatchLiteral() {
        val query = "var=0"
        val config = SCMConfiguration().also { it.query = arrayOf(query) }
        val cloneMatcher = CloneMatcher(config)
        val matchingResult: List<CodeFragment> = cloneMatcher.match(Paths.get("./src/test/sample/Sample.java"))
        assertEquals(7, matchingResult.size)
        println(matchingResult)
    }
}
