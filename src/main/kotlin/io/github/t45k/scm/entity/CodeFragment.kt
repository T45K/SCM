package io.github.t45k.scm.entity

import java.nio.file.Path

data class CodeFragment(val path: Path, val beginLine: Int, val endLine: Int)

infix fun Pair<Path, Int>.to(endLine: Int): CodeFragment = CodeFragment(this.first, this.second, endLine)
