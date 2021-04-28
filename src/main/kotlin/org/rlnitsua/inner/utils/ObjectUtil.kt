package org.rlnitsua.inner.utils

fun checkElementIndex(index: Int, size: Int) {
    if (index < 0 || index >= size) {
        throw IndexOutOfBoundsException("index-$index is out of bound of size-$size")
    }
}