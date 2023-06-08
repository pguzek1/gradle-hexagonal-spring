package pl.alfn.project.core.services

import io.kotest.core.spec.style.ShouldSpec

abstract class ValidationSpec(body: ShouldSpec.() -> Unit) : ShouldSpec(body)