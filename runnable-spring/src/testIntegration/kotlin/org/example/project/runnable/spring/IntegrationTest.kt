package org.example.project.runnable.spring

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.delay

class IntegrationTest : ShouldSpec ({

    should("1 == 1") {
        val asd = IntegrationTest::class.java.getResource("/asdf.txt")?.readText()

        DomainTest("asd")

        delay(5000)

        println(asd)

        asd shouldBe "testIntegration"
    }

    should("1 == 1 main") {
        val asd = IntegrationTest::class.java.getResource("/asdf_main.txt")?.readText()

        delay(5000)

        println(asd)

        asd shouldBe "asdfdsa"
    }

})