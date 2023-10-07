package nuricanozturk.dev.android.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import nuricanozturk.dev.android.repositorylib.dal.PaymentApplicationHelper
import nuricanozturk.dev.android.repositorylib.entity.User
import java.time.LocalDate
import java.time.Month
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    @Inject
    lateinit var helper : PaymentApplicationHelper;
    override fun onCreate(savedInstanceState : Bundle?)
    {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user1 = User("halil", "halil123", "Halil", "OZTURK",
            LocalDate.of(1937, Month.APRIL, 25), LocalDate.now())
        helper.userRepository.save(user1)
        Toast.makeText(this, helper.getHelloMessage(), Toast.LENGTH_LONG).show()
    }
}