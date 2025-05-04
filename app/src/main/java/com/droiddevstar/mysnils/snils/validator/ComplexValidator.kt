package com.droiddevstar.mysnils.snils.validator

import com.droiddevstar.mysnils.snils.validator.ValidationResult.Companion.valid

open class ComplexValidator private constructor(
    private val validators: List<Validator>
): Validator {
    override fun validate(data: String) =
        validators.fold(
            initial = valid(value = data)
        ) { res, validator ->
            res.andThen(validator)
        }

    companion object {
        fun build(validators: List<Validator>): ComplexValidator {
            return ComplexValidator(validators)
        }
    }
}