package nuricanozturk.dev.android.payment;

import static nuricanozturk.dev.android.payment.global.BundleKeysKt.LOGIN_INFO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.karandev.util.data.service.DataServiceException;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import nuricanozturk.dev.android.app.data.service.PaymentAppDataService;
import nuricanozturk.dev.android.app.data.service.dto.LoginInfoDTO;
import nuricanozturk.dev.android.payment.databinding.ActivityLoginBinding;
import nuricanozturk.dev.android.payment.viewmodel.LoginActivityListenerViewModel;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity
{
    private ActivityLoginBinding m_loginActivityBinding;

    @Inject
    PaymentAppDataService m_service;

    private void init()
    {
        initBinding();
    }

    private void initBinding()
    {
        m_loginActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        m_loginActivityBinding.setViewModel(new LoginActivityListenerViewModel(this));
        m_loginActivityBinding.setLoginInfo(new LoginInfoDTO());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        init();
    }

    public void loginButtonClicked()
    {
        try
        {
            var loginInfo = m_loginActivityBinding.getLoginInfo();
            if (m_service.checkAndSaveLoginInfo(loginInfo))
            {
                Toast.makeText(this, "Access granted", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, OperationsActivity.class).putExtra(LOGIN_INFO, loginInfo));
            }
            else Toast.makeText(this, "Access denied!", Toast.LENGTH_LONG).show();

        }
        catch (DataServiceException ex)
        {
            Toast.makeText(this, "Data Problem: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        catch (Throwable ex)
        {
            Toast.makeText(this, "Problem occured: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}