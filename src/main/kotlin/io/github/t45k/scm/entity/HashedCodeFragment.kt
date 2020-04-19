package io.github.t45k.scm.entity

data class HashedCodeFragment(val hash: Int, val codeFragment: CodeFragment)

infix fun Int.to(codeFragment: CodeFragment): HashedCodeFragment = HashedCodeFragment(this, codeFragment)
