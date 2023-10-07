package nuricanozturk.dev.android.repositorylib

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import nuricanozturk.dev.android.repositorylib.entity.User
import nuricanozturk.dev.android.repositorylib.global.USER_FILE

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Ignore
import java.io.File
import java.time.LocalDate
import java.time.Month

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@Ignore("tested before")
class ExampleInstrumentedTest
{
    companion object {
        val appContext  = InstrumentationRegistry.getInstrumentation().targetContext
        val userRepository = UserRepository(appContext)
    }

    @Before
    fun setUp()
    {
        val file = File(appContext.filesDir, USER_FILE)

        file.delete()
        val user1 = User("halil", "halil123", "Halil", "OZTURK",
            LocalDate.of(1937, Month.APRIL, 25), LocalDate.now())
        val user2 = User("nuricanozturk", "nuri321", "Nuri", "Can", "ÖZTÜRK",
            LocalDate.of(1999, Month.JANUARY, 25), LocalDate.now())

        userRepository.save(user1)
        userRepository.save(user2)
    }


    @Test
    fun saveAndFindByUsernameAndPasswordSuccessTest()
    {
        assertNotNull(userRepository.findByUserNameAndPassword("nuricanozturk", "nuri321"))
    }


    @Test
    fun saveAndFindByUsernameAndPasswordPasswordFailTest()
    {
        assertNull(userRepository.findByUserNameAndPassword("nuricanozturk", "dsa"))
    }
    @Test
    fun saveAndFindByUsernameAndPasswordUsernameFailTest()
    {
        assertNull(userRepository.findByUserNameAndPassword("canozturk", "nuri321"))
    }

    @Test
    fun saveAndFindByUsernameAndPasswordBothFailTest()
    {
        assertNull(userRepository.findByUserNameAndPassword("yok", "koy"))
    }


    @Test
    fun existsByUserNameAndPasswordSuccessTest()
    {
        assertTrue(userRepository.existsByUsernameAndPassword("halil", "halil123"))
    }

    @Test
    fun existsByUserNameAndPasswordPasswordFailTest()
    {
        assertFalse(userRepository.existsByUsernameAndPassword("halil", "halil12"))
    }

    @Test
    fun existsByUserNameAndPasswordUsernameFailTest()
    {
        assertFalse(userRepository.existsByUsernameAndPassword("halill", "halil123"))
    }

    @Test
    fun existsByUserNameAndPasswordBothFailTest()
    {
        assertFalse(userRepository.existsByUsernameAndPassword("yagmur", "adasdas"))
    }
}