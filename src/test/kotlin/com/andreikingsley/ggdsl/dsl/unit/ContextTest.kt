package com.andreikingsley.ggdsl.dsl.unit

import com.andreikingsley.ggdsl.dsl.*
import com.andreikingsley.ggdsl.ir.Geom
import com.andreikingsley.ggdsl.ir.Layer
import com.andreikingsley.ggdsl.ir.Layout
import com.andreikingsley.ggdsl.ir.Plot
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

internal class ContextTest {
    @Test
    fun testPoints() {
        val context = PlotContext().apply {
            points {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    mapOf(),
                    Geom.POINT,
                    mapOf(),
                    mapOf()
                )
            ),
            context.layers
        )
    }

    @Test
    fun testLine() {
        val context = PlotContext().apply {
            line {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    mapOf(),
                    Geom.LINE,
                    mapOf(),
                    mapOf()
                )
            ),
            context.layers
        )
    }

    @Test
    fun testBars() {
        val context = PlotContext().apply {
            bars {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    mapOf(),
                    Geom.BAR,
                    mapOf(),
                    mapOf()
                )
            ),
            context.layers
        )
    }

    @Test
    fun testPlotEmpty(){
        val plot = plot {  }
        assertEquals(
            Plot(
                mapOf(),
                listOf(),
                Layout(),
                mapOf()
            ),
            plot
        )
    }
}