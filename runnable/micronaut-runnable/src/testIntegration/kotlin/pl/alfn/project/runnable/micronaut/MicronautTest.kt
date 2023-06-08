package pl.alfn.project.runnable.micronaut

import io.kotest.core.spec.style.ShouldSpec
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import jakarta.inject.Inject

@MicronautTest
class DemoTest: ShouldSpec() {

    @Inject
    lateinit var application: EmbeddedApplication<*>

    init {
        should("test the server is running") {
            assert(false)
        }
    }
}