package org.rlnitsua.random

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.text.NumberFormat
import kotlin.random.Random

const val TEST_ORIGIN_FILE_PATH = "./dat/Origin0.dat"


class RandomFile {
    private var time = 0.0

    fun generateLegalFile(elementSize: Int, sampleSize: Int) {
        val originFile = File(TEST_ORIGIN_FILE_PATH)
        val bufferedWriter = BufferedWriter(FileWriter(originFile))

        // write first line
        val line = StringBuilder().apply {
            append("time").append(randomSpaces())
            (0 until elementSize).onEach {
                this.append(randomName())
                if (it != elementSize - 1) {
                    this.append(randomSpaces())
                }
            }
        }

        bufferedWriter.write(line.toString())
        bufferedWriter.newLine()

        (0 until sampleSize).onEach { sampleIndex ->
            line.apply {
                clear()
                append(randomTime()).append(randomSpaces())
                (0 until elementSize).onEach {
                    this.append(randomValue())
                    if (it != elementSize - 1) {
                        this.append(randomSpaces())
                    }
                }
            }

            bufferedWriter.write(line.toString())
            if (sampleIndex != sampleSize - 1) {
                bufferedWriter.newLine()
            }
        }

        bufferedWriter.close()
    }

    private fun randomTime(): String {
        time += Random.nextDouble() * 10
        return NumberFormat.getInstance().apply {
            maximumFractionDigits = 2
        }.format(time)
    }
}
