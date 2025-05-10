package com.droiddevstar.mysnils

import com.droiddevstar.mysnils.snils.filter.FilterMaxLength
import com.droiddevstar.mysnils.snils.filter.FilterOnlyDigits
import com.droiddevstar.mysnils.snils.filter.filter
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.be
import io.kotest.matchers.should

class SnilsFilterTest : FunSpec({
    context("Snils filter") {
        val snilsFilter = filter {
            +FilterOnlyDigits
            +FilterMaxLength(11)
        }

        withData(
            listOf(
                "            " to "",
                "sd fasdf as fsd a fas asd f" to "",
                "s6d84f65sd46s5d4f" to "684654654",
                "123-456-789" to "123456789",
                "123-456-789 11" to "12345678911",
                "123-456" to "123456",
                "123-456-789-123-456-789" to "12345678912",
                "123456789123456789" to "12345678912",
            )
        ) { (data, res) ->
            snilsFilter.filter(data) should be(res)
        }
    }
})