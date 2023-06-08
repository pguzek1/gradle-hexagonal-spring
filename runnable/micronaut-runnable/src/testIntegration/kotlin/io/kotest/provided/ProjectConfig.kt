package io.kotest.provided

import io.kotest.core.config.AbstractProjectConfig
import io.micronaut.test.extensions.kotest5.MicronautKotest5Extension

@Suppress("unused")
object ProjectConfig : AbstractProjectConfig() {
    override fun extensions() = super.extensions() + MicronautKotest5Extension
}