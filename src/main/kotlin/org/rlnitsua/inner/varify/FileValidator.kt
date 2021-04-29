package org.rlnitsua.inner.varify

import org.rlnitsua.inner.exception.FileIllegalException
import org.rlnitsua.inner.utils.parseOriginalFileLine
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader

fun checkFileLegal(filePath: String) {
    val file = File(filePath)
    if (!file.exists()) {
        throw FileNotFoundException("file $filePath is not found !")
    }

    val bufferedReader = BufferedReader(FileReader(file))
    var line: String? = bufferedReader.readLine()
    val elementCount = parseOriginalFileLine(line ?: "").size
    var lineNum = 1

    while (line != null) {
        val otherLineElementCount = parseOriginalFileLine(line).size
        if (elementCount != otherLineElementCount) {
            bufferedReader.close()
            throw FileIllegalException("Line ${lineNum}th element size is : $otherLineElementCount" +
                    ",but other lines is $elementCount !")
        }
        line = bufferedReader.readLine()
        lineNum++
    }
    bufferedReader.close()

    if (lineNum < 2) {
        throw FileIllegalException("The content of the file must be larger than two lines !")
    }
}