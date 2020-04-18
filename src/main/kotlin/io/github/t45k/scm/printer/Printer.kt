package io.github.t45k.scm.printer

import io.github.t45k.scm.entity.CodeFragment
import java.nio.file.Files
import java.nio.file.Path

class Printer {
    private val fileContents = mutableMapOf<Path, List<String>>()

    fun printResult(clones: List<CodeFragment>) {
        println("${clones.size} clones are detected")
        for (clone in clones) {
            println(clone.path)
            val contents: List<String> = fileContents.computeIfAbsent(clone.path, Files::readAllLines)
            (clone.startLine - 1 until clone.endLine)
                .map { "%5d: ".format(it) + contents[it] }
                .forEach(::println)
        }
    }
}
