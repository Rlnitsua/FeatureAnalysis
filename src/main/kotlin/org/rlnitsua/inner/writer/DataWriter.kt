package org.rlnitsua.inner.writer

import org.rlnitsua.inner.data.MetaData
import org.rlnitsua.inner.utils.log
import java.io.*

private const val INTERMEDIATE_FILE_PATH = "./IntermediateFile.dat"
private const val TIME_DEFAULT_NAME = "time"
private const val SPACE = " "
private const val UNDERLINE = "_"
private const val COMMA = ","

class DataWriter(private val targetFilePath: String) : IDataWriter {
    private var isFileGenerate = false
    private val intermediateFile: File = File(INTERMEDIATE_FILE_PATH)
    private val imBufferedWriter = BufferedWriter(FileWriter(intermediateFile))

    init {
//        intermediateFile.deleteOnExit()
    }

    override fun <T : Comparable<T>, P : Pair<T, T>> writeData(metaData: MetaData<T, P>) {
        if (isFileGenerate) {
            throw RuntimeException("Target file has been generated !!")
        }

        if (intermediateFile.length().toInt() != 0) {
            imBufferedWriter.newLine()
        }
        val line = StringBuilder().apply {
            // append element name
            append(metaData.elementName).append(UNDERLINE)
            for (i in metaData.data().indices) {
                // append time
                append(metaData.data()[i].first)
                // append ,
                append(COMMA)
                // append value
                append(metaData.data()[i].second)
                // append _
                if (i != metaData.data().size - 1) {
                    append(UNDERLINE)
                }
            }
        }.toString()
        imBufferedWriter.write(line)
        imBufferedWriter.flush()
    }

    override fun generateOutputFile() {
        log("start generateOutputFile")
        close(imBufferedWriter)
        val imBufferedReader = BufferedReader(FileReader(intermediateFile))
        imBufferedReader.mark(intermediateFile.length().toInt() + 1)
        val targetBufferedWriter = BufferedWriter(FileWriter(File(targetFilePath)))

        log("start write the first line")
        // write first line
        val sampleSize = writeElementNameLine(imBufferedReader, targetBufferedWriter)
        targetBufferedWriter.newLine()
        log("first line end")

        // write other line
        for (i in 0 until sampleSize) {
            log("start write the ${i}th line")
            writeValueLine(imBufferedReader, targetBufferedWriter, i + 1)
            if (i != sampleSize - 1) {
                targetBufferedWriter.newLine()
            }
            log("write the ${i}th line end")
        }

        close(imBufferedReader, targetBufferedWriter)
        isFileGenerate = true
        log("end generateOutputFile")
    }

    private fun writeElementNameLine(
        imBufferedReader: BufferedReader,
        targetBufferedWriter: BufferedWriter
    ): Int {
        var sampleSize = 0
        val line = StringBuffer().apply {
            append(TIME_DEFAULT_NAME).append(SPACE)
        }

        val iterator = imBufferedReader.lineSequence().iterator()
        while (iterator.hasNext()) {
            val currentLine = iterator.next()
            line.append(currentLine.split(UNDERLINE)[0])
            line.append(SPACE)
            sampleSize = currentLine.split(UNDERLINE).size - 1
        }

        targetBufferedWriter.write(line.substring(0, line.length - 1))
        targetBufferedWriter.flush()
        imBufferedReader.reset()
        return sampleSize
    }

    private fun writeValueLine(
        imBufferedReader: BufferedReader,
        targetBufferedWriter: BufferedWriter, sampleIndex: Int
    ) {
        val line = StringBuffer()
        // write time
        val time = imBufferedReader.readLine()
            .split(UNDERLINE)[sampleIndex]
            .split(COMMA)[0]
//        val time = 1.1 // mock
        line.append(time).append(SPACE)
        imBufferedReader.reset()

        // write value
        val iterator = imBufferedReader.lineSequence().iterator()
        while (iterator.hasNext()) {
            val value = iterator.next()
                .split(UNDERLINE)[sampleIndex]
                .split(COMMA)[1]
//            val value = "1.23" // mock
            line.append(value).append(SPACE)
        }

        targetBufferedWriter.write(line.substring(0, line.length - 1))
        targetBufferedWriter.flush()
        imBufferedReader.reset()
    }

    private fun close(vararg closeableArr: Closeable) {
        for (closeable in closeableArr) {
            closeable.close()
        }
    }
}