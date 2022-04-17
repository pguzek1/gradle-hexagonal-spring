package me.project.adapter.spring.reactive.config.extension

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.extensions.Extension
import io.kotest.extensions.spring.SpringExtension

class SpringExtensionConfig :AbstractProjectConfig() {

    override fun extensions(): List<Extension> = listOf(SpringExtension)

}