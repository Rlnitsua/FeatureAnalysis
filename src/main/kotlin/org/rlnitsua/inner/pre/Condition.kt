package org.rlnitsua.inner.pre

import org.rlnitsua.inner.data.MetaData

interface Condition {
    fun atThisTime(metaData: MetaData<Double, Pair<Double, Double>>): Double
}