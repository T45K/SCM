package io.github.t45k.scm

import org.kohsuke.args4j.CmdLineException
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.io.StringReader
import java.nio.file.Paths
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

        val reader = BufferedReader(StringReader(outputStream.toString()))
        assertEquals("2 clones are detected", reader.readLine())
        assertEquals(Paths.get("./src/test/sample/Sample.java"), Paths.get(reader.readLine()))
        assertEquals("    2:         return a > b ? a : b;", reader.readLine())

        assertEquals(Paths.get("./src/test/sample/Sample.java"), Paths.get(reader.readLine()))
        assertEquals("    7:                 x > y", reader.readLine())
        assertEquals("    8:                         ? x", reader.readLine())
        assertEquals("    9:                         :", reader.readLine())
        assertEquals("   10:                         y", reader.readLine())
    }

    @Test
    fun testMainWithSourceFile() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        val args: Array<String> = arrayOf("-s", "./src/test/sample/Sample.java", "-q", "a", ">", " b", "?", " a", ":", "b")
        main(args)
        val reader = BufferedReader(StringReader(outputStream.toString()))
        assertEquals("2 clones are detected", reader.readLine())
        assertEquals(Paths.get("./src/test/sample/Sample.java"), Paths.get(reader.readLine()))
        assertEquals("    2:         return a > b ? a : b;", reader.readLine())

        assertEquals(Paths.get("./src/test/sample/Sample.java"), Paths.get(reader.readLine()))
        assertEquals("    7:                 x > y", reader.readLine())
        assertEquals("    8:                         ? x", reader.readLine())
        assertEquals("    9:                         :", reader.readLine())
        assertEquals("   10:                         y", reader.readLine())
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