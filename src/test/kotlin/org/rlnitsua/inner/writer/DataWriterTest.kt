package org.rlnitsua.inner.writer

import org.junit.Test
import org.rlnitsua.inner.utils.log
import org.rlnitsua.random.randomMetaData

class DataWriterTest {

    // 11 - 1w 数据
    // 裸跑了 54s,802ms
    // 去掉 line.split 解析之后，21s,248ms
    // 去掉 time 解析之后，18s,334ms
    // -----
    // 目标是 100 * 100_0000 时间：600s
    // 100 * 1_0000 时间为: 6s | 当前: 2m 28s 93ms(去掉解析)
    // 去掉循环 5s 342ms
    @Test
    fun testMiddle() {
        val dataWriter = DataWriter("./dat/targetFile.dat")
        log("start")
        (0 until 100).onEach {
            val data = randomMetaData(10000)
            dataWriter.writeData(data)
            log("writeFile-$it done")
        }
        log("All Files done")
        dataWriter.generateOutputFile()
        log("Job's done")
    }

    @Test
    fun testSimple() {
        val dataWriter = DataWriter("./dat/target_simple.dat")
        (0 until 10).onEach {
            val data = randomMetaData(100)
            dataWriter.writeData(data)
        }
        dataWriter.generateOutputFile()
    }
}