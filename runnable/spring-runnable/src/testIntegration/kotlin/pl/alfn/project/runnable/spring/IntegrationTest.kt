package pl.alfn.project.runnable.spring

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest

//@AutoConfigureWebTestClient
//@SpringBootTest(
//    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
//)
//class IntegrationTest(
////    val userService: UserService,
////    val restTemplate: TestRestTemplate
//) : ShouldSpec({
//
//    should("example test 1") {
//        val fileContent = IntegrationTest::class.java.getResource("/example.txt")?.readText()
//
//        println(fileContent)
//
//        fileContent shouldBe "integrationTest"
//    }
//
//    should("example test 2") {
//        val fileContent = IntegrationTest::class.java.getResource("/example_main.txt")?.readText()
//
//        println(fileContent)
//
//        fileContent shouldBe "main"
//    }
//
//    should("example test 3") {
//        val model = UnitTestModel("test")
//
//        model.value shouldBe "test"
//    }
//
//})