package nuricanozturk.dev.android.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import nuricanozturk.dev.android.repositorylib.dal.PaymentApplicationHelper
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

    }
}