package com.andreikingsley.ggdsl.dsl.unit

import com.andreikingsley.ggdsl.dsl.categorical
import com.andreikingsley.ggdsl.dsl.categoricalPos
import com.andreikingsley.ggdsl.dsl.continuous
import com.andreikingsley.ggdsl.dsl.continuousPos
import com.andreikingsley.ggdsl.ir.scale.*
import com.andreikingsley.ggdsl.util.color.Color
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ScaleTest {

    @Test
    fun testContinuousPosDefault() {
        assertEquals(PositionalContinuousDefaultScale, continuousPos())
    }

    @Test
    fun testContinuousPos() {
        val limits = -5 to 19
        val scale = continuousPos(limits)
        assertEquals(PositionalContinuousScale(limits), scale)
    }

    @Test
    fun testCategoricalPosDefault() {
        assertEquals(PositionalCategoricalDefaultScale, categoricalPos())
    }

    @Test
    fun testCategoricalPos() {
        val categories = listOf("a", "b", "CCC", "123")
        val scale = categoricalPos(categories = categories)
        assertEquals(PositionalCategoricalScale(categories), scale)
    }

    @Test
    fun testContinuousDefault() {
        assertEquals(NonPositionalContinuousDefaultScale, continuous())
    }

    @Test
    fun testContinuous() {
        val domainLimits = 23.0 to 129.13
        val rangeLimits = 0.0F to 1.0F
        val scale = continuous(domainLimits, rangeLimits)
        assertEquals(NonPositionalContinuousScale(domainLimits, rangeLimits), scale)
    }

    @Test
    fun testCategoricalDefault() {
        assertEquals(NonPositionalCategoricalDefaultScale, categorical())
    }

        @Test
    fun testCategorical() {
        val domainCategories = listOf(1, 3, 100, 999)
        val rangeCategories = listOf(Color.RED, Color.BLACK, Color.BLUE, Color.WHITE)
        val scale = categorical(domainCategories, rangeCategories)
        assertEquals(NonPositionalCategoricalScale(domainCategories, rangeCategories), scale)
    }
}
