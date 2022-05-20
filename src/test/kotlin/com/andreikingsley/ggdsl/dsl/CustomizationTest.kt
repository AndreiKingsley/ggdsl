package com.andreikingsley.ggdsl.dsl

import com.andreikingsley.ggdsl.ir.Geom
import com.andreikingsley.ggdsl.ir.Layer
import com.andreikingsley.ggdsl.ir.Layout
import com.andreikingsley.ggdsl.ir.Plot
import com.andreikingsley.ggdsl.ir.aes.MappableNonPositionalAes
import com.andreikingsley.ggdsl.ir.aes.X
import com.andreikingsley.ggdsl.ir.aes.Y
import com.andreikingsley.ggdsl.ir.bindings.*
import com.andreikingsley.ggdsl.ir.data.NamedData
import com.andreikingsley.ggdsl.ir.feature.FeatureName
import com.andreikingsley.ggdsl.ir.feature.LayerFeature
import com.andreikingsley.ggdsl.ir.feature.PlotFeature
import com.andreikingsley.ggdsl.ir.scale.NonPositionalCategoricalScale
import com.andreikingsley.ggdsl.ir.scale.PositionalContinuousDefaultScale
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals

internal class CustomizationTest {

    companion object {
        val customGeom = Geom("custom")
        val SPECIFIC_AES = MappableNonPositionalAes<CustomGeomType>("customAes")
    }

    data class CustomGeomType(val name: String)

    class CustomGeomContext(override var data: MutableNamedData) : LayerContext() {
        val specificAes = SPECIFIC_AES
    }

    fun PlotContext.customLayer(block: CustomGeomContext.() -> Unit) {
        layers.add(CustomGeomContext(data).apply { copyFrom(this@customLayer) }.apply(block).toLayer(customGeom))
    }

    data class MockLayerFeature(val value: Int): LayerFeature {
        override val featureName = FEATURE_NAME
        companion object {
            val FEATURE_NAME = FeatureName("mock_layer_feature")
        }
    }

    fun LayerContext.mockFeatureFunction(value: Int) {
        features[MockLayerFeature.FEATURE_NAME] = MockLayerFeature(value)
    }

    data class MockPlotFeature(val title: String): PlotFeature {
        override val featureName = FEATURE_NAME
        companion object {
            val FEATURE_NAME = FeatureName("mock_plot_feature")
        }
    }

    var PlotContext.mockFeatureProp: String
        get() = ""
        set(value) {
            features[MockPlotFeature.FEATURE_NAME] = MockPlotFeature(value)
        }

    val mockSrcDouble = source<Double>("mock_double")
    val mockSrcInt = source<Int>("mock_int")
    val mockSrcString = source<String>("mock_string")
    val mockSrcFloat = source<Float>("mock_float")

    val dataset: NamedData = mapOf()

    @Test
    fun testCustomLayer() {
        val plot = plot(dataset) {
            y(mockSrcDouble)
            customLayer {
                x(mockSrcFloat.scaled(continuousPos()))
                specificAes(mockSrcString.scaled(
                    categorical(
                        listOf("A", "B"),
                        listOf(CustomGeomType("x"), CustomGeomType("xxx"))
                    )
                ))
            }
            customLayer {
                x(mockSrcFloat)
                specificAes(CustomGeomType("yy"))
            }
        }
        assertEquals(
            Plot(
                dataset,
                listOf(
                    Layer(
                        dataset,
                        customGeom,
                        mapOf(
                            X to ScaledPositionalDefaultMapping(
                                X,
                                SourceScaledPositionalDefault(mockSrcFloat, PositionalContinuousDefaultScale),
                                typeOf<Float>()
                            ),
                            Y to ScaledUnspecifiedDefaultMapping(
                                Y,
                                SourceScaledUnspecifiedDefault(mockSrcDouble),
                                typeOf<Double>()
                            ),
                            SPECIFIC_AES to ScaledNonPositionalMapping(
                                SPECIFIC_AES,
                                SourceScaledNonPositional(
                                    mockSrcString,
                                    NonPositionalCategoricalScale(
                                        listOf("A", "B"),
                                        listOf(CustomGeomType("x"), CustomGeomType("xxx"))
                                    )
                                ),
                                typeOf<String>(),
                                typeOf<CustomGeomType>()
                            )
                        ),
                        mapOf(),
                        mapOf()
                    ),
                    Layer(
                        dataset,
                        customGeom,
                        mapOf(
                            X to ScaledUnspecifiedDefaultMapping(
                                X,
                                SourceScaledUnspecifiedDefault(mockSrcFloat),
                                typeOf<Float>()
                            ),
                            Y to ScaledUnspecifiedDefaultMapping(
                                Y,
                                SourceScaledUnspecifiedDefault(mockSrcDouble),
                                typeOf<Double>()
                            )
                        ),
                        mapOf(
                            SPECIFIC_AES to NonPositionalSetting(
                                SPECIFIC_AES,
                                CustomGeomType("yy")
                            )
                        ),
                        mapOf()
                    )
                ),
                Layout(),
                mapOf()
            ),
            plot
        )
    }

    @Test
    fun testFeatures() {
        val plot = plot(dataset) {
            mockFeatureProp = "amentes ineptias"
            bars {
                mockFeatureFunction(14)
            }
        }

        assertEquals(
            Plot(
                dataset,
                listOf(
                    Layer(
                        dataset,
                        Geom.BAR,
                        mapOf(),
                        mapOf(),
                        mapOf(MockLayerFeature.FEATURE_NAME to MockLayerFeature(14))
                    )
                ),
                Layout(),
                mapOf(MockPlotFeature.FEATURE_NAME to MockPlotFeature("amentes ineptias"))
            ),
            plot
        )
    }
}