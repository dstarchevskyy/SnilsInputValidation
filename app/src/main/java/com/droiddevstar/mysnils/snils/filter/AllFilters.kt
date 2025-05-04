package com.droiddevstar.mysnils.snils.filter

object FilterOnlyDigits : Filter {
    override fun filter(data: String): String = data.filter { it.isDigit() }
}

class FilterMaxLength(private val maxLength: Int) : Filter {
    override fun filter(data: String): String = data.take(n = maxLength)
}