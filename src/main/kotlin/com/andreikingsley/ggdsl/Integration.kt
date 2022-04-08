package com.andreikingsley.ggdsl

import org.jetbrains.kotlinx.jupyter.api.annotations.JupyterLibrary
import org.jetbrains.kotlinx.jupyter.api.libraries.*

@JupyterLibrary
internal class Integration : JupyterIntegration() {

    override fun Builder.onLoaded() {
        import("com.andreikingsley.ggdsl.dsl.*")
        import("com.andreikingsley.ggdsl.ir.data.*")
        import("com.andreikingsley.ggdsl.util.color.*")
        import("com.andreikingsley.ggdsl.util.symbol.*")
        import("com.andreikingsley.ggdsl.util.linetype.*")
    }

}
