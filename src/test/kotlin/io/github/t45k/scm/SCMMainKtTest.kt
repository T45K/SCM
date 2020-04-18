package io.github.t45k.scm

import org.kohsuke.args4j.CmdLineException
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class SCMMainKtTest {

    @Test
    fun testMainWithInputDir() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        val args: Array<String> = arrayOf("-i", "./src/test/sample", "-q", "a", ">", " b", "?", " a", ":", "b")
        main(args)

        val output = """HashedCodeFragment(path=./src/test/sample/Sample.java, hash=1495876587, beginLine=3, endLine=3)
           |HashedCodeFragment(path=./src/test/sample/Sample.java, hash=1495876587, beginLine=8, endLine=11)
           |""".trimMargin()
        assertEquals(output, outputStream.toString())
    }

    @Test
    fun testMainWithSourceFile() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        val args: Array<String> = arrayOf("-s", "./src/test/sample/Sample.java", "-q", "a", ">", " b", "?", " a", ":", "b")
        main(args)

        val output = """HashedCodeFragment(path=./src/test/sample/Sample.java, hash=1495876587, beginLine=3, endLine=3)
           |HashedCodeFragment(path=./src/test/sample/Sample.java, hash=1495876587, beginLine=8, endLine=11)
           |""".trimMargin()
        assertEquals(output, outputStream.toString())
    }

    @Test
    fun testMainWithNoInputException() {
        val args: Array<String> = arrayOf("-q", "a", ">", " b", "?", " a", ":", "b")
        assertFailsWith<NoInputException> { main(args) }
    }

    @Test
    fun testMainWithCmdLineException() {
        val args: Array<String> = arrayOf()
        assertFailsWith<CmdLineException> { main(args) }
    }
}