package com.droiddevstar.mysnils.snils.validator

interface ValidationError {
    fun getErrorMessageStringResId(): Int
}

sealed class ValidationResult {
    abstract val data: String
    abstract fun isValid(): Boolean

    class Valid(override val data: String): ValidationResult() {
        override fun isValid(): Boolean = true
    }

    class Error(
        override val data: String,
        val errors: List<ValidationError>
    ): ValidationResult() {
        override fun isValid(): Boolean = false
    }

    private fun bind(anotherValidationFunction: (String) -> ValidationResult): ValidationResult {
        return when(this) {
            is Error -> {
                when (val res = anotherValidationFunction(data)) {
                    is Error -> invalid(
                        value = res.data,
                        errors = this.errors + res.errors
                    )
                    is Valid -> invalid(
                        value = res.data,
                        errors = this.errors
                    )
                }
            }

            is Valid -> {
                anotherValidationFunction(data)
            }
        }
    }

    fun andThen(anotherValidator: Validator): ValidationResult =
        bind {
            str: String -> anotherValidator.validate(str)
        }

    companion object {
        fun valid(value: String): ValidationResult = Valid(value)
        fun invalid(value: String, errors: List<ValidationError>): ValidationResult {
            assert(errors.isNotEmpty())
            return Error(value, errors)
        }

        fun invalid(value: String, error: ValidationError): ValidationResult {
            return Error(value, listOf(error))
        }
    }
}

fun String.asValid() : ValidationResult = ValidationResult.valid(this)

