package io.github.t45k.scm

import io.github.t45k.scm.matching.CloneMatcher
import org.kohsuke.args4j.CmdLineException
import org.kohsuke.args4j.CmdLineParser
import java.nio.file.Files
import kotlin.streams.toList

fun main(args: Array<String>) {
    val config = SCMConfiguration()
    val parser = CmdLineParser(config)

    try {
        parser.parseArgument(*args)
    } catch (e: CmdLineException) {
        println(parser.printUsage(System.out))
        throw e
    }

    val cloneMatcher = CloneMatcher(config.query.joinToString(" "))
    when {
        config.isInputDirInitialized() -> cloneMatcher.search(Files.walk(config.inputDir).filter { it.toString().endsWith(".java") }.toList())
        config.isSourceFileInitialized() -> cloneMatcher.search(config.sourceFile)
        else -> throw NoInputException("Specify Directory or File")
    }.forEach(::println)
}

class NoInputException(override val message: String) : RuntimeException()
