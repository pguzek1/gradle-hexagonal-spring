package pl.alfn.project.core.domain

import io.kotest.core.spec.style.ShouldSpec

abstract class ValidationSpec(body: ShouldSpec.() -> Unit) : ShouldSpec(body)