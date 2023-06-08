package pl.alfn.project.core.services.user

import com.fasterxml.uuid.UUIDType.TIME_BASED_EPOCH
import com.fasterxml.uuid.impl.UUIDUtil
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.date.shouldNotBeAfter
import io.kotest.matchers.date.shouldNotBeBefore
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.mockk.*
import java.time.Instant.now
import java.util.UUID.randomUUID
import pl.alfn.project.core.services.ValidationSpec
import pl.alfn.project.ports.driven.user.IUserFindQueryDriven
import pl.alfn.project.ports.driven.user.IUserPersistCommandDriven
import pl.alfn.project.ports.model.user.FindUserQueryDriving
import pl.alfn.project.ports.model.user.PersistUserCommandDriving
import pl.alfn.project.ports.model.user.UserPto
import pl.alfn.project.ports.model.util.PersistenceInfoPto

class UserServiceTest : ValidationSpec({
    val userFindQuery = mockk<IUserFindQueryDriven>()
    val userPersistCommand = mockk<IUserPersistCommandDriven>()

    val userService = UserService(userFindQuery, userPersistCommand)

    should("find user when user exists") {
        // given
        val uuid = randomUUID()
        val now = now()
        val query = FindUserQueryDriving(id = uuid)
        val expected = UserPto(
            firstName = "firstName",
            lastName = "lastName",
            persistenceInfo = PersistenceInfoPto(
                id = uuid,
                version = 0,
                createdAt = now,
                updatedAt = now
            )
        )

        coEvery { userFindQuery.findUser(any()) } returns expected

        // when
        val result = userService.findUser(query)

        // then
        coVerify {
            userFindQuery.findUser(withArg {
                it.id shouldBe uuid
            })
        }
        result shouldBeSameInstanceAs expected
    }

    should("throw UserNotFoundException when user not found") {
        // given
        val uuid = randomUUID()
        val query = FindUserQueryDriving(id = uuid)
        val expected = UserNotFoundException(uuid = uuid)

        coEvery { userFindQuery.findUser(any()) } throws expected

        // when
        val result = shouldThrow<UserNotFoundException> { userService.findUser(query) }

        // then
        coVerify {
            userFindQuery.findUser(withArg {
                it.id shouldBe uuid
            })
        }
        result shouldBeSameInstanceAs expected
    }

    should("persist user") {
        // given
        val uuid = randomUUID()
        val command = PersistUserCommandDriving(
            firstName = "FirstName",
            lastName = "LastName",
        )

        coEvery { userPersistCommand.persistUser(any()) } returns uuid

        // when
        val timestampBefore = now()
        val result = userService.persistUser(command)
        val timestampAfter = now()

        // then
        coVerify {
            userPersistCommand.persistUser(withArg { it ->
                it.firstName shouldBe "FirstName"
                it.lastName shouldBe "LastName"
                it.persistenceInfo.let {
                    UUIDUtil.typeOf(it.id) shouldBe TIME_BASED_EPOCH
                    it.version shouldBe 0
                    it.createdAt shouldNotBeBefore timestampBefore
                    it.createdAt shouldNotBeAfter timestampAfter
                    it.updatedAt shouldNotBeBefore timestampBefore
                    it.updatedAt shouldNotBeAfter timestampAfter
                }
            })
        }
        result shouldBe uuid
    }
})