package io.github.t45k.scm.matching

import io.github.t45k.scm.entity.CodeFragment
import java.nio.file.Path

interface ICloneMatcher {
    fun search(paths: List<Path>): List<CodeFragment>
    fun search(path: Path): List<CodeFragment>
}
