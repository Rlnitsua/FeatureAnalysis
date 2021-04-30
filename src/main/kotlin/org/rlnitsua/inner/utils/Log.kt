package org.rlnitsua.inner.utils

import java.util.logging.Level
import java.util.logging.Logger

private const val DEBUG = true

fun log(message: String) {
    if (DEBUG) {
        Logger.getGlobal().log(Level.INFO, message)
    }
}