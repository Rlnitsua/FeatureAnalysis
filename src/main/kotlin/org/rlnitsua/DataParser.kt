package org.rlnitsua

import org.rlnitsua.inner.pre.generateIntermediateFile
import org.rlnitsua.inner.varify.checkFileLegal

class DataParser(private val filePath: String) : IDataParser {

    init {
        checkFileLegal(filePath)
        generateIntermediateFile(filePath)
    }

    override fun queryDataByName(name: String) {
        TODO("Not yet implemented")
    }
}