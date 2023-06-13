package com.github.availability.ad.compat

import com.github.availability.ad.core.Ad

internal object StringBuilderCompat {

    private fun StringBuilder.br(): StringBuilder {
        return append("\n")
    }

    private fun StringBuilder.appendValue(
        value: Any,
        prefix: String = "",
        suffix: String = ""
    ): StringBuilder {
        if (prefix.isNotEmpty()) {
            append(prefix)
        }
        append(if (value.toString().isEmpty()) "null" else value)
        if (suffix.isNotEmpty()) {
            append(suffix)
        }
        return br()
    }

    internal fun Ad.formatString(): String {
        val builder = StringBuilder()
        builder.appendValue("[")
        builder.appendValue(config.key, "\tkey : ")
        builder.appendValue(sourceName, "\tsourceName : ")
        builder.appendValue(responseId, "\tresponseId : ")
        builder.appendValue(mediationName, "\tmediationName : ")
        builder.appendValue(latencyMillis, "\tlatencyMillis : ")
        builder.appendValue(expireMillis, "\texpireMillis : ")
        builder.appendValue(failure ?: "", "\tfailure : ")
        builder.appendValue("]")
        return builder.toString()
    }

}