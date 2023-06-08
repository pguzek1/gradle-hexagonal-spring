package pl.alfn.project.runnable.spring

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.delay

class UnitTest : ShouldSpec({

    should("1 == 1") {
        delay(1000)
        1 shouldBe 1
    }

})