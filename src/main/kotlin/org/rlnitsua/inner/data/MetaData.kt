package org.rlnitsua.inner.data

import org.rlnitsua.inner.calculate.ICalculator
import org.rlnitsua.inner.utils.checkElementIndex

class MetaData<T : Comparable<T>, P : Pair<T, T>>(val elementName: String) : IDataContainer<P>, ICalculator<P>{
    // pair data container
    private val pairList: MutableList<P> = mutableListOf()

    override fun add(pair : P): Boolean = pairList.add(pair)

    override fun size() = pairList.size

    override fun data(): List<P> = pairList

    override fun max(): P = maxOfRange(0, size())

    override fun maxOfRange(start: Int, end: Int): P {
        checkElementIndex(start, size())
        checkElementIndex(end - 1, size())

        return pairList.filterIndexed { index, _ ->
            index in start until end
        }.maxBy {
            it.second
        }!!
    }

    override fun min(): P = minOfRange(0, size())

    override fun minOfRange(start: Int, end: Int): P {
        checkElementIndex(start, size())
        checkElementIndex(end - 1, size())

        return pairList.filterIndexed { index, _ ->
            index in start until end
        }.minBy {
            it.second
        }!!
    }
}