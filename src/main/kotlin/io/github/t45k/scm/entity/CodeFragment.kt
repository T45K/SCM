package io.github.t45k.scm.entity

import java.nio.file.Path

data class CodeFragment(val path: Path, val startLine: Int, val endLine: Int)
