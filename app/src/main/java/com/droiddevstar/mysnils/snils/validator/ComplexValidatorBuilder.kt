package com.droiddevstar.mysnils.snils.validator

class ComplexValidatorBuilder() {
    private val validators: MutableList<Validator> = mutableListOf()

    fun build(): ComplexValidator {
        return ComplexValidator.build(validators)
    }

    operator fun Validator.unaryPlus(): ComplexValidatorBuilder {
        validators.add(this)
        return this@ComplexValidatorBuilder
    }
}

fun validator(
    lambda: ComplexValidatorBuilder.() -> ComplexValidatorBuilder
): ComplexValidator {
    return ComplexValidatorBuilder().lambda().build()
}
