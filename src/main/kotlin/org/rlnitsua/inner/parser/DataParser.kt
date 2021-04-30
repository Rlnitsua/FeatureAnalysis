package org.rlnitsua.inner.parser

import org.rlnitsua.inner.data.MetaData
import org.rlnitsua.inner.pre.generateIntermediateFile
import org.rlnitsua.inner.verify.Verifiable
import org.rlnitsua.inner.verify.checkOriginalFileLegal

class DataParser<T: Comparable<T>, P: Pair<T, T>>(private val filePath: String)
    : IDataParser, Verifiable {

    override fun parse() {
        checkOriginalFileLegal(filePath)
        generateIntermediateFile(filePath)
    }

    override fun <T : Comparable<T>, P : Pair<T, T>> queryDataByName(name: String): MetaData<T, P> {
        TODO("Not yet implemented")
    }

    override fun checkLegal() {
        checkOriginalFileLegal(filePath)
    }
}