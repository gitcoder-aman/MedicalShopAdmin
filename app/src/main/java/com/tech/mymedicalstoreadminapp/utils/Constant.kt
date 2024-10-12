package com.tech.mymedicalstoreadminapp.utils

const val BASE_URL = "https://aman93578.pythonanywhere.com/"

fun isInteger(input: String): Boolean {
    return try {
        input.toInt()
        true
    } catch (e: NumberFormatException) {
        false
    }
}
fun isFloat(input: String): Boolean {
    return try {
        input.toFloat()
        true
    } catch (e: NumberFormatException) {
        false
    }
}