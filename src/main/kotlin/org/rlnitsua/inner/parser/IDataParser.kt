package org.rlnitsua.inner.parser

import org.rlnitsua.inner.data.MetaData

interface IDataParser {
    fun parse()
    fun <T: Comparable<T>, P: Pair<T, T>> queryDataByName(name: String): MetaData<T, P>
}