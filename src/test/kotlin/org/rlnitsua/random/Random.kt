package org.rlnitsua.random

import org.rlnitsua.inner.data.MetaData
import java.math.BigDecimal
import java.text.NumberFormat
import kotlin.random.Random

fun randomName(): String {
    val list = mutableListOf<Char>().apply {
        "1234567890abcdefghijklmnopqrstuvwxyz".forEach { this.add(it) }
    }
    return StringBuilder().apply {
        (1..(4..5).random()).onEach {
            append(list.random())
        }
    }.toString()
}

fun randomValue(): String {
    return NumberFormat.getInstance().apply {
        maximumFractionDigits = 2
    }.format(Random.nextDouble() * 10)
}

fun randomSpaces(): String {
    return StringBuilder().apply {
        (1..(1..5).random()).onEach {
            this.append(" ")
        }
    }.toString()
}

fun randomMetaData(sampleSize: Int): MetaData<Double, Pair<Double, Double>>{
    var time = 0.0
    val metaData = MetaData<Double, Pair<Double, Double>>(randomName())

    for (i in 0 until sampleSize) {
        time += Random.nextDouble() * 3
        time = BigDecimal(time).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()
        metaData.add(Pair(time, randomValue().toDouble()))
    }

    return metaData
}