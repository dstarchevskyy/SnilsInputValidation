package com.droiddevstar.mysnils.snils.formatter

class SnilsFormatter : Formatter {
    override fun format(data: String): String =
        buildString {
            data.forEachIndexed { index, c ->
                when (index) {
                    0, 1, 2 -> append(c)
                    3 -> append('-').append(c)
                    4, 5 -> append(c)
                    6 -> append('-').append(c)
                    7, 8 -> append(c)
                    9 -> append(' ').append(c)
                    10 -> append(c)
                    else -> append(c)
                }
            }
        }
}
