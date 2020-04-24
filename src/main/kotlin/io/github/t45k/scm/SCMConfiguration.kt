package io.github.t45k.scm

import org.kohsuke.args4j.CmdLineException
import org.kohsuke.args4j.CmdLineParser
import org.kohsuke.args4j.Option
import org.kohsuke.args4j.OptionDef
import org.kohsuke.args4j.spi.Messages
import org.kohsuke.args4j.spi.OptionHandler
import org.kohsuke.args4j.spi.Parameters
import org.kohsuke.args4j.spi.PathOptionHandler
import org.kohsuke.args4j.spi.Setter
import java.nio.file.Path

class SCMConfiguration {
    @Option(name = "-i", aliases = ["--input-dir"], usage = "Input directory", handler = PathOptionHandler::class)
    lateinit var inputDir: Path

    @Option(name = "-s", aliases = ["--source-file"], usage = "Single source file", handler = PathOptionHandler::class)
    lateinit var sourceFile: Path

    @Option(name = "-q", aliases = ["--query"], usage = "Query", required = true, handler = QueryHandler::class)
    lateinit var query: Array<String>

    @Option(name = "-a", aliases = ["--algorithm"], usage = "Algorithm")
    var algorithms: Algorithms = Algorithms.ROLLING_HASH

    fun isInputDirInitialized(): Boolean = ::inputDir.isInitialized
    fun isSourceFileInitialized(): Boolean = ::sourceFile.isInitialized

    enum class Algorithms { ROLLING_HASH, SUFFIX_ARRAY }
}

class QueryHandler(parser: CmdLineParser, option: OptionDef, setter: Setter<String>)
    : OptionHandler<String>(parser, option, setter) {
    override fun getDefaultMetaVariable(): String {
        return Messages.DEFAULT_META_STRING_ARRAY_OPTION_HANDLER.format()
    }

    @Throws(CmdLineException::class)
    override fun parseArguments(params: Parameters): Int {
        (0 until params.size())
            .map { params.getParameter(it) }
            .forEach(setter::addValue)

        return params.size()
    }
}
