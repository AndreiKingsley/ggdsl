package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.Geom
import com.andreikingsley.ggdsl.ir.Layer
import com.andreikingsley.ggdsl.ir.bindings.*
import com.andreikingsley.ggdsl.ir.Layout
import com.andreikingsley.ggdsl.ir.Plot
import com.andreikingsley.ggdsl.ir.aes.*
import com.andreikingsley.ggdsl.ir.data.NamedData
import com.andreikingsley.ggdsl.ir.scale.NonPositionalCategoricalScale
import com.andreikingsley.ggdsl.ir.scale.PositionalContinuousDefaultScale
import com.andreikingsley.ggdsl.ir.scale.PositionalContinuousScale
import com.andreikingsley.ggdsl.util.color.Color
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals

internal class CommonTest {

    @Test
    fun oneLayer() {
        val dataset: NamedData = mapOf(
            "x" to listOf(1.0, 2.0, 3.0),
            "y" to listOf(3F, 12F, 5.5F),
            "type" to listOf("A", "B", "A")
        )
        val srcX = source<Double>("x")
        val srcY = source<Float>("y")
        val type = source<String>("type")

        val plot = plot(dataset) {
            x(srcX)
            points {
                y(srcY)
                color(type)
                size(4.5)
            }
        }

        assertEquals(
            Plot(
                dataset,
                listOf(
                    Layer(
                        dataset,
                        Geom.POINT,
                        mappings = mapOf(
                            X to ScaledUnspecifiedDefaultMapping(
                                X, SourceScaledUnspecifiedDefault(srcX), typeOf<Double>()
                            ),
                            Y to ScaledUnspecifiedDefaultMapping(
                                Y, SourceScaledUnspecifiedDefault(srcY), typeOf<Float>()
                            ),
                            COLOR to ScaledUnspecifiedDefaultMapping(
                                COLOR, SourceScaledUnspecifiedDefault(type), typeOf<String>()
                            ),
                        ),
                        settings = mapOf(
                            SIZE to NonPositionalSetting(SIZE, 4.5)
                        )
                    )
                ),
                Layout(),
            ),
            plot
        )
    }

    @Test
    fun severalLayersAndScales() {
        val dataset: NamedData = mapOf(
            "width" to listOf(1.0, 2.0, 3.0, 3.0),
            "height" to listOf(3F, 12F, 5.5F, 8F),
            "type" to listOf("A", "B", "A", "B"),
            "number of attachments" to listOf(2, 5, 2, 4),
        )
        val width = source<Double>("width")
        val height = source<Float>("height")
        val type = source<String>("type")
        val noa = source<Int>("number of attachments")

        val plot = plot(dataset) {
            x(width.scaled(continuousPos()))
            y(
                height.scaled(
                    continuousPos(
                        1f to 15f
                    )
                )
            )
            points {
                color(
                    type.scaled(
                        categorical(
                            domainCategories = listOf("A", "B"),
                            rangeValues = listOf(Color.RED, Color.fromName("blue"))
                        )
                    )
                )
                size(noa)
            }
            bars {
                width(5.0)
            }
        }

        val xMapping = ScaledPositionalDefaultMapping(
            X, SourceScaledPositionalDefault(width, PositionalContinuousDefaultScale), typeOf<Double>()
        )
        val yMapping = ScaledPositionalMapping(
            Y, SourceScaledPositional(
                height, PositionalContinuousScale(
                    limits = 1f to 15f
                )
            ), typeOf<Float>()
        )
        val colorMapping = ScaledNonPositionalMapping(
            COLOR,
            SourceScaledNonPositional(
                type,
                NonPositionalCategoricalScale(
                    domainCategories = listOf("A", "B"),
                    rangeValues = listOf(Color.RED, Color.fromName("blue"))
                )
            ),
            typeOf<String>(),
            //typeOf<Color>()
        )

        assertEquals(
            Plot(
                dataset,
                listOf(
                    Layer(
                        dataset,
                        Geom.POINT,
                        mappings = mapOf(
                            X to xMapping,
                            Y to yMapping,
                            COLOR to colorMapping,
                            SIZE to ScaledUnspecifiedDefaultMapping(
                                SIZE, SourceScaledUnspecifiedDefault(noa), typeOf<Int>()
                            )
                        ),
                        settings = mapOf()
                    ),
                    Layer(
                        dataset,
                        Geom.BAR,
                        mappings = mapOf(
                            X to xMapping,
                            Y to yMapping,
                        ),
                        settings = mapOf(
                            WIDTH to NonPositionalSetting(WIDTH, 5.0)
                        )
                    )
                ),
                Layout(),
            ),
            plot
        )
    }

    @Test
    fun datasetOverriding(){
        val emptyDataset: NamedData = mapOf()
        val realDataset: NamedData = mapOf(
            "name" to listOf("Tiny", "Pudge", "Spirit breaker"),
            "winRate" to listOf(100.0, 0.01, 50.0),
            "iq" to listOf(12, 12, 1000)
        )
        val wr = source<Double>("winRate")
        val iq = source<Int>("iq")
        val name = source<String>("name")

        val plot = plot(emptyDataset) {
           // data = emptyDataset.toMutableMap()
            x(wr)
            y(iq)
            points {
                data = realDataset.toMutableMap()
                color(name)
            }
        }
        assertEquals(
            Plot(
                emptyDataset,
                listOf(
                    Layer(
                        realDataset,
                        Geom.POINT,
                        mappings = mapOf(
                            X to ScaledUnspecifiedDefaultMapping(
                                X, SourceScaledUnspecifiedDefault(wr), typeOf<Double>()
                            ),
                            Y to ScaledUnspecifiedDefaultMapping(
                                Y, SourceScaledUnspecifiedDefault(iq), typeOf<Int>()
                            ),
                            COLOR to ScaledUnspecifiedDefaultMapping(
                                COLOR, SourceScaledUnspecifiedDefault(name), typeOf<String>()
                            ),
                        ),
                        settings = mapOf()
                    )
                ),
                Layout(),
            ),
            plot
        )
    }
}