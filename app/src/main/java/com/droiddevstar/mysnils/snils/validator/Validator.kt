package com.droiddevstar.mysnils.snils.validator

interface Validator {
    fun validate(data: String): ValidationResult
}