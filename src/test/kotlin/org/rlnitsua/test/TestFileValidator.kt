package org.rlnitsua.test

import org.junit.Test
import org.rlnitsua.inner.varify.checkFileLegal

class TestFileValidator {

    @Test
    fun test() {
        checkFileLegal("./dat/Origin0.dat")
    }
}