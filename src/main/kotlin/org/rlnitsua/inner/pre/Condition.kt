package org.rlnitsua.inner.pre

import org.rlnitsua.inner.data.MetaData
import org.rlnitsua.inner.data.Pair

interface Condition {
    fun atThisTime(metaData: MetaData<Pair>): Double
}