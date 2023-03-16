package com.example.springdatarestreactadmin

import java.util.logging.Logger

// Return logger for Java class, if companion object fix the name
fun <T : Any> logger(forClass: Class<T>): Logger {
    return Logger.getLogger(forClass.name.split("$$")[0])
}

// Return logger for Java class, if companion object fix the name
fun <T : Any> T.logger(): Lazy<Logger> {
    return lazy { getLogger() }
}

// return logger from extended class (or the enclosing class)
fun <T : Any> T.getLogger(): Logger {
    return logger(this.javaClass)
}