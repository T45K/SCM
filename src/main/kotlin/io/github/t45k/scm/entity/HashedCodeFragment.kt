package io.github.t45k.scm.entity

import java.nio.file.Path

data class HashedCodeFragment(val path: Path, val hash: Long, val beginLine: Int, val endLine: Int)
