package org.rlnitsua

import org.junit.Test
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class TestIO {

    @Test
    fun testIO() {
        val file = File("./data/Origin.dat")
        val bufferedReader = BufferedReader(FileReader(file))

        while (true) {
            // readLine 还是有可能为空的，只是显示有问题，确实是 String？ 而不是 String！
            val line = bufferedReader.readLine()
            println(line!!)
        }
    }
}