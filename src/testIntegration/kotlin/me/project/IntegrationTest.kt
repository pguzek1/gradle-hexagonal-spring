package me.project

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class IntegrationTest : ShouldSpec ({

    should("1 == 1") {
        val asd = IntegrationTest::class.java.getResource("/asdf.txt")?.readText()

        println(asd)

        asd shouldBe "test"
    }

})