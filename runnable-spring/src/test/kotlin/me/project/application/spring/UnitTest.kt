package me.project.application.spring

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.delay

class UnitTest : ShouldSpec ({

    should("1 == 1") {
        delay(5000)
        1 shouldBe 1
    }

})