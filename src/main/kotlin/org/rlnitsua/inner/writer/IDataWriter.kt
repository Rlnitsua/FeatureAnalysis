package org.rlnitsua.inner.writer

import org.rlnitsua.inner.data.MetaData

interface IDataWriter {
    fun <T: Comparable<T>, P: Pair<T, T>> writeData(metaData: MetaData<T, P>)
    fun generateOutputFile()
}