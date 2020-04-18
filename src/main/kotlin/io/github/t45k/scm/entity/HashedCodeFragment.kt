package io.github.t45k.scm.entity

data class HashedCodeFragment(val hash: Long, val codeFragment: CodeFragment)

infix fun Long.to(codeFragment: CodeFragment): HashedCodeFragment = HashedCodeFragment(this, codeFragment)
