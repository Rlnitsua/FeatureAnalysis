package org.rlnitsua.test

import org.junit.Test
import org.rlnitsua.inner.verify.checkOriginalFileLegal

class TestFileValidator {

    @Test
    fun test() {
        checkOriginalFileLegal("./dat/Origin0.dat")
    }
}