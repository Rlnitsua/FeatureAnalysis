package org.rlnitsua.test

import org.junit.Test
import org.rlnitsua.random.RandomFile

class TestRandomFile {

    @Test
    fun test() {
        RandomFile().generateLegalFile(50, 10_0000)
    }
}