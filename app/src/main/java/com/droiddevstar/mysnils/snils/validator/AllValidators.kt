package com.droiddevstar.mysnils.snils.validator

import com.droiddevstar.mysnils.R
import com.droiddevstar.mysnils.snils.validator.ValidationResult.Companion.invalid
import com.droiddevstar.mysnils.snils.validator.ValidationResult.Companion.valid

data object SnilsCheckSumValidationError : ValidationError {
    override fun getErrorMessageStringResId(): Int = R.string.error_invalid_check_sum
}

object OnlyDigitsValidationError : ValidationError {
    override fun getErrorMessageStringResId(): Int = R.string.error_only_digits
}

object ExactLengthValidationError : ValidationError {
    override fun getErrorMessageStringResId(): Int = R.string.error_invalid_length
}

object EmptyStringValidationError : ValidationError {
    override fun getErrorMessageStringResId(): Int = R.string.error_enter_snils
}

class ExactLengthValidator(private val exactLength: Int) : Validator {
    override fun validate(data: String): ValidationResult =
        if (data.length == exactLength) {
            valid(data)
        } else {
            invalid(data, ExactLengthValidationError)
        }
}

object EmptyStringValidator : Validator {
    override fun validate(data: String): ValidationResult  =
        if (data.isEmpty()) {
            invalid(data, EmptyStringValidationError)
        } else {
            valid(data)
        }
    }

object OnlyDigitsValidator : Validator {
    override fun validate(data: String): ValidationResult =
        if (data.all { it.isDigit() }) {
            valid(data)
        } else {
            invalid(data, OnlyDigitsValidationError)
        }
    }

object SnilsCheckSumValidator : Validator {
    override fun validate(data: String): ValidationResult {
        if (data.length != 11) {
            return invalid(data, SnilsCheckSumValidationError)
        }

        try {
            val part1: String = data.substring(0, 9)
            val part2: String = data.substring(9, 11)

            val checkSum = part1
                .reversed()
                .mapIndexed { index, c -> c.digitToInt() * (index + 1) }
                .sum()
                .mod(101)
                .toString()
                .padStart(2, '0')
                .takeLast(2)

            return if (checkSum == part2) {
                valid(data)
            } else {
                invalid(data, SnilsCheckSumValidationError)
            }
        } catch (e: Exception) {
            return invalid(data, SnilsCheckSumValidationError)
        }
    }

}