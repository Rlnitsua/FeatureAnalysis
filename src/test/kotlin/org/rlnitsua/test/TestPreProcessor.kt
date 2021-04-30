package org.rlnitsua.test

import org.junit.Test
import org.rlnitsua.inner.data.MetaData
import org.rlnitsua.inner.pre.Translation
import org.rlnitsua.inner.pre.generateIntermediateFile

private const val FILE_PATH = "./dat/Origin.dat"

class TestPreProcessor {

    @Test
    fun testDefault() {
        generateIntermediateFile(FILE_PATH)
    }

    @Test
    fun testCondition() {
        generateIntermediateFile(FILE_PATH, "b", object : Translation {
            override fun atThisTime(metaData: MetaData<Double, Pair<Double, Double>>): Double {
                return metaData.data().find {
                    it.second == 100.0
                }?.first ?: 0.0
            }
        })
    }
}