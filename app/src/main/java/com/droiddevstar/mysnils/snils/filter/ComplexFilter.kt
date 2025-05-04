package com.droiddevstar.mysnils.snils.filter

class ComplexFilter private constructor(
    private val filters: List<Filter>
) : Filter {
    override fun filter(data: String): String =
        filters.fold(data) { res, filter ->
            filter.filter(res)
        }

    companion object {
        fun build(filters: List<Filter>): ComplexFilter {
            return ComplexFilter(filters)
        }
    }
}



