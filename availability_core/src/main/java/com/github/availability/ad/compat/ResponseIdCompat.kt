package com.github.availability.ad.compat

object ResponseIdCompat {

    private const val CUSTOM_RESPONSE_CHARS =
        "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"

    fun customAdRequestResponseId(): String {
        return (1..(18..26).random())
            .map { CUSTOM_RESPONSE_CHARS.random() }
            .joinToString("")
    }

}