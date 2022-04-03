package com.andreikingsley.ggdsl

import com.andreikingsley.ggdsl.dsl.add
import com.andreikingsley.ggdsl.dsl.plot

fun main(){
    val p = plot(dataset = mapOf()) {}
    p.add {
        dataset
    }
}