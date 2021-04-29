package org.rlnitsua.inner.data

interface IDataContainer<P> {
    fun add(pair: P): Boolean
    fun size(): Int
    fun data(): List<P>
}