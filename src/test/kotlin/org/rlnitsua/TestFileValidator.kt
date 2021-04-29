package org.rlnitsua

import org.junit.Test
import org.rlnitsua.inner.varify.checkFileLegal

class TestFileValidator {

    @Test
    fun test() {
        checkFileLegal("./dat/Origin.dat")
    }
}