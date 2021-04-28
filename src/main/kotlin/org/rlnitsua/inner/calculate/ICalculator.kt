package org.rlnitsua.inner.calculate

interface ICalculator<P : Comparable<P>> {
    fun max(): P
    fun maxOfRange(start: Int, end: Int): P

    fun min(): P
    fun minOfRange(start: Int, end: Int): P
}