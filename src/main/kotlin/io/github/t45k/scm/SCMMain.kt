package io.github.t45k.scm

import io.github.t45k.scm.matching.CloneMatcher
import io.github.t45k.scm.printer.Printer
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
    val printer = Printer()
    when {
        config.isInputDirInitialized() -> cloneMatcher.search(Files.walk(config.inputDir).filter { it.toString().endsWith(".java") }.toList())
        config.isSourceFileInitialized() -> cloneMatcher.search(config.sourceFile)
        else -> throw NoInputException("Specify Directory or File")
    }.let(printer::printResult)
}

class NoInputException(override val message: String) : RuntimeException()
