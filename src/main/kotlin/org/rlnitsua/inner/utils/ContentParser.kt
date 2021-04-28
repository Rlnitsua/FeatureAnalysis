package org.rlnitsua.inner.utils

fun parseOriginalFileLine(line: String): List<String> {
    val newLine = line.replace(" {2,}".toRegex(), " ")
    return newLine.split(" ")
}