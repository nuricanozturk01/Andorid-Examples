package nuricanozturk.dev.android.repositorylib

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import nuricanozturk.dev.android.repositorylib.entity.Payment
import nuricanozturk.dev.android.repositorylib.entity.User
import nuricanozturk.dev.android.repositorylib.global.PAYMENT_FILE
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
@RunWith(AndroidJUnit4::class) //@Ignore("tested before")
class PaymentInstrumentedTest
{
    companion object
    {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val paymentRepository = PaymentRepository(appContext)
        val userRepository = UserRepository(appContext)
    }

    @Before
    fun setUp()
    {
        val file = File(appContext.filesDir, USER_FILE)
        val file2 = File(appContext.filesDir, PAYMENT_FILE)

        file.delete()
        file2.delete()
        val user1 =
            User("halil", "halil123", "Halil", "OZTURK", LocalDate.of(1937, Month.APRIL, 25), LocalDate.now())
        val user2 =
            User("nuricanozturk", "nuri321", "Nuri", "Can", "ÖZTÜRK", LocalDate.of(1999, Month.JANUARY, 25), LocalDate.now())

        val payment1 = Payment(1, user1.username, 3.3, 5.0, "bilgisayar")
        val payment2 = Payment(2, user2.username, 10.0, 10.0, "telefon")
        val payment3 = Payment(3, user2.username, 10.0, 10.0, "deneme")

        userRepository.save(user1)
        userRepository.save(user2)

        paymentRepository.save(payment1)
        paymentRepository.save(payment2)
        paymentRepository.save(payment3)
    }

    @Test
    fun save_and_findByUsernameSizeTest()
    {
        assertEquals(2, paymentRepository.findByUserName("nuricanozturk").size)
    }

    @Test
    fun save_and_checkEqualQuantity()
    {
        assertTrue(5.0 - paymentRepository.findByUserName("halil")[0].quantity < 0.001)
    }
}