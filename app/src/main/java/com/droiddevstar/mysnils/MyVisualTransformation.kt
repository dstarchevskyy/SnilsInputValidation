package com.droiddevstar.mysnils

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.droiddevstar.mysnils.snils.filter.ComplexFilter
import com.droiddevstar.mysnils.snils.filter.FilterMaxLength
import com.droiddevstar.mysnils.snils.filter.FilterOnlyDigits
import com.droiddevstar.mysnils.snils.filter.filter
import com.droiddevstar.mysnils.snils.formatter.SnilsFormatter

private val snilsFilter: ComplexFilter = filter {
    +FilterOnlyDigits
    +FilterMaxLength(11)
}

private val snilsFormatter: SnilsFormatter = SnilsFormatter()

private var isMaxLengthExceeded: Boolean = false

object MyVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        isMaxLengthExceeded = (text.text.length > MAX_LENGTH)

        val filteredText: String = snilsFilter.filter(text.text)
        val formattedText: String = snilsFormatter.format(filteredText)

        val annotatedString = AnnotatedString(formattedText)

        val offsetMapping: OffsetMapping = createOffsetMapping(
            original = text.text,
            formatted = formattedText
        )

        return TransformedText(
            text = annotatedString,
            offsetMapping = offsetMapping
        )
    }
}

private fun createOffsetMapping(
    original: String,
    formatted: String
): OffsetMapping {
    return object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 0) return 0

            if (isMaxLengthExceeded) return offset - 1

            // Count how many digits we have before the offset in original
            val digitsBefore: Int = original.take(offset).count { it.isDigit() }

            // Find position in formatted string where we have this many digits
            var digitCount: Int = 0
            for (i: Int in formatted.indices) {
                if (formatted[i].isDigit()) {
                    digitCount++
                    if (digitCount == digitsBefore) {
                        // If we're at the end of original, return position after last digit
                        if (offset == original.length) {
                            return i + 1
                        }
                        return i + 1 // position after this digit
                    }
                }
            }

            return formatted.length
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 0) return 0

            // Count how many digits we have before the offset in formatted
            val digitsBefore = formatted.take(offset).count { it.isDigit() }

            // Find position in original string where we have this many digits
            var digitCount = 0
            for (i in original.indices) {
                if (original[i].isDigit()) {
                    digitCount++
                    if (digitCount == digitsBefore) {
                        // If we're at the end of formatted, return position after last digit
                        if (offset == formatted.length) {
                            return i + 1
                        }
                        return i + 1 // position after this digit
                    }
                }
            }

            return original.length
        }
    }
}

private const val MAX_LENGTH: Int = 11