package com.droiddevstar.mysnils

import com.droiddevstar.mysnils.snils.filter.FilterMaxLength
import com.droiddevstar.mysnils.snils.filter.FilterOnlyDigits
import com.droiddevstar.mysnils.snils.filter.filter
import io.kotest.common.ExperimentalKotest
import io.kotest.matchers.be
import io.kotest.matchers.should
import org.junit.Test

@OptIn(ExperimentalKotest::class)
class SnilsFilterTest {
    val snilsFilter = filter {
        +FilterOnlyDigits
        +FilterMaxLength(11)
    }

    @Test
    fun testTest() {
        val sourceTargetMap: Map<String, String> = mapOf(
            "      " to "",
            "asdcas dckja sdkjc" to "",
            "asdfa1dsf2sdfd3" to "123",
            "123-456-789" to "123456789",
            "123-456-789 11" to "12345678911",
            "123-456" to "123456",
            "123-456-789-123-456-789" to "12345678912",
            "123456789123456789" to "12345678912"
        )

        sourceTargetMap.forEach { source, target ->
            snilsFilter.filter(source) should be(target)
        }
    }


}
