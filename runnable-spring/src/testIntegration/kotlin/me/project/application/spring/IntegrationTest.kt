package me.project.application.spring

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.delay

class IntegrationTest : ShouldSpec ({

    should("1 == 1") {
        val asd = IntegrationTest::class.java.getResource("/asdf.txt")?.readText()

        delay(5000)

        println(asd)

        asd shouldBe "testIntegration"
    }

})