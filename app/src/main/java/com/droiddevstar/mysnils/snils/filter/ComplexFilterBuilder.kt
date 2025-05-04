package com.droiddevstar.mysnils.snils.filter

class ComplexFilterBuilder {
    private val filters: MutableList<Filter> = mutableListOf()

    fun build(): ComplexFilter {
        return ComplexFilter.build(filters)
    }

    operator fun Filter.unaryPlus(): ComplexFilterBuilder {
        filters.add(this)
        return this@ComplexFilterBuilder
    }
}

fun filter(lambda: ComplexFilterBuilder.() -> ComplexFilterBuilder): ComplexFilter {
    return ComplexFilterBuilder().lambda().build()
}