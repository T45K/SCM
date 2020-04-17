package io.github.t45k.scm.matching

import io.github.t45k.scm.entity.HashedCodeFragment
import java.nio.file.Path

interface ICloneMatcher {
    fun search(paths: List<Path>): List<HashedCodeFragment>
    fun search(path: Path): List<HashedCodeFragment>
}
