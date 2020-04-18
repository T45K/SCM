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
    companion object {
        val output: List<String> = """2 clones are detected
            |./src/test/sample/Sample.java
            |    2:         return a > b ? a : b;
            |./src/test/sample/Sample.java
            |    7:                 x > y
            |    8:                         ? x
            |    9:                         :
            |   10:                         y""".trimMargin().split("\n")
    }

    @Test
    fun testMainWithInputDir() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        val args: Array<String> = arrayOf("-i", "./src/test/sample", "-q", "a", ">", " b", "?", " a", ":", "b")
        main(args)

        val reader = BufferedReader(StringReader(outputStream.toString()))
        assertEquals(output[0], reader.readLine())
        assertEquals(Paths.get(output[1]), Paths.get(reader.readLine()))
        assertEquals(output[2], reader.readLine())
        reader.readLine()
        assertEquals(Paths.get(output[3]), Paths.get(reader.readLine()))
        assertEquals(output[4], reader.readLine())
        assertEquals(output[5], reader.readLine())
        assertEquals(output[6], reader.readLine())
        assertEquals(output[7], reader.readLine())
    }

    @Test
    fun testMainWithSourceFile() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        val args: Array<String> = arrayOf("-s", "./src/test/sample/Sample.java", "-q", "a", ">", " b", "?", " a", ":", "b")
        main(args)
        val reader = BufferedReader(StringReader(outputStream.toString()))
        assertEquals(output[0], reader.readLine())
        assertEquals(Paths.get(output[1]), Paths.get(reader.readLine()))
        assertEquals(output[2], reader.readLine())
        reader.readLine()
        assertEquals(Paths.get(output[3]), Paths.get(reader.readLine()))
        assertEquals(output[4], reader.readLine())
        assertEquals(output[5], reader.readLine())
        assertEquals(output[6], reader.readLine())
        assertEquals(output[7], reader.readLine())
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