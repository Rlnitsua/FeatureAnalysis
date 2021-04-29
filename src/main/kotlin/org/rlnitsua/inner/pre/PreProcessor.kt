package org.rlnitsua.inner.pre

import org.rlnitsua.inner.data.MetaData
import org.rlnitsua.inner.utils.parseOriginalFileLine
import java.io.*
import java.lang.StringBuilder

const val INTERMEDIATE_FILE = "./data/IntermediateFile.dat"

fun generateIntermediateFile(filePath: String,
                             targetElementName: String? = null,
                             condition: Condition? = null) {
    if (targetElementName == null || condition == null) {
        generateIntermediateFile(filePath, 0.0)
    } else {
        val startTime = calculateTime(filePath, targetElementName, condition)
        generateIntermediateFile(filePath, startTime)
    }
}

private fun calculateTime(filePath: String,
                          targetElementName: String,
                          condition: Condition): Double {
    val file = File(filePath)
    val bufferedReader = BufferedReader(FileReader(file))

    val metaData = MetaData<Double, Pair<Double, Double>>(targetElementName)
    var line: String? = bufferedReader.readLine()
    val targetElementIndex = findTargetElementIndex(line ?: "", targetElementName)
    val iterator = bufferedReader.lineSequence().iterator()

    while (iterator.hasNext()) {
        line = iterator.next()
        val contentList = parseOriginalFileLine(line)
        metaData.add(Pair(contentList[0].toDouble(), contentList[targetElementIndex].toDouble()))
    }

    bufferedReader.close()
    return condition.atThisTime(metaData)
}

private fun generateIntermediateFile(filePath: String, startTime: Double) {
    val originalFile = File(filePath)
    val bufferedReader = BufferedReader(FileReader(originalFile))

    val newFile = File(INTERMEDIATE_FILE)
    val bufferedWriter = BufferedWriter(FileWriter(newFile))

    val line = bufferedReader.readLine()
    bufferedReader.mark(originalFile.length().toInt())
    val elementNameList = parseOriginalFileLine(line)

    for (currentElementIndex in 1 until elementNameList.size) {
        val newFileLineContent = StringBuilder()
        // first element
        newFileLineContent.append( "${elementNameList[currentElementIndex]}_")
        // write content
        bufferedReader.reset()
        val iterator = bufferedReader.lineSequence().iterator()
        while (iterator.hasNext()) {
            val contentList = parseOriginalFileLine(iterator.next())
            val currentTime = contentList[0].toDouble()
            if (currentTime < startTime) {
                continue
            } else {
                val updateTime = currentTime - startTime
                newFileLineContent.append("${updateTime},${contentList[currentElementIndex]}_")
            }
        }
        // remove the last '_'
        newFileLineContent.substring(0, newFileLineContent.length - 1)
        // write content and a new line
        bufferedWriter.write(newFileLineContent.toString())
        if (currentElementIndex != elementNameList.size - 1) {
            bufferedWriter.newLine()
        }
        bufferedWriter.flush()
    }

    bufferedReader.close()
    bufferedWriter.close()
}

private fun findTargetElementIndex(line: String, targetElementName: String): Int {
    var targetElementIndex = 0
    val elementNameList = parseOriginalFileLine(line)
    for (i in elementNameList.indices) {
        if (elementNameList[i] == targetElementName) {
            targetElementIndex = i
        }
    }
    return targetElementIndex
}