//package pl.alfn.project.adapters.spring.persistence.inmemory
//
//import io.kotest.assertions.throwables.shouldThrow
//import io.kotest.matchers.shouldBe
//import io.kotest.matchers.shouldNotBe
//import java.time.Instant
//import java.util.UUID
//import pl.alfn.project.adapters.spring.persistence.inmemory.config.SpringBootIntegrationSpec
//import pl.alfn.project.adapters.spring.persistence.inmemory.exception.UserNotFoundException
//import pl.alfn.project.adapters.spring.persistence.inmemory.repository.UserInMemoryDatabaseRepository
//import pl.alfn.project.ports.model.user.FindUserQueryDriven
//import pl.alfn.project.ports.model.user.UserPto
//import pl.alfn.project.ports.model.util.PersistenceInfoPto
//
//class UserInMemoryDatabaseTest(
//    val userInMemoryDatabase: UserInMemoryDatabaseRepository
//) : SpringBootIntegrationSpec() {
//    init {
//
//        should("create user database bean") {
//            userInMemoryDatabase shouldNotBe null
//        }
//
//        should("throw exception when user not found") {
//            // given
//            val id = UUID.randomUUID()
//
//            // when
//            val result = shouldThrow<UserNotFoundException> {
//                userInMemoryDatabase.findUser(FindUserQueryDriven(id = id))
//            }
//
//            // then
//            result.message shouldBe "User '$id' not found"
//        }
//
//        should("persist user and return it") {
//            // given
//            val expected = UserPto(
//                firstName = "Jan",
//                lastName = "Kowalski",
//                persistenceInfo = PersistenceInfoPto(
//                    id = UUID.randomUUID(),
//                    version = 0,
//                    createdAt = Instant.now(),
//                    updatedAt = Instant.now()
//                )
//            )
//            // when
//            val uuid = userInMemoryDatabase.persistUser(expected)
//
//            // then
//            val result = userInMemoryDatabase.findUser(FindUserQueryDriven(uuid))
//            result shouldBe expected
//        }
//
//    }
//}