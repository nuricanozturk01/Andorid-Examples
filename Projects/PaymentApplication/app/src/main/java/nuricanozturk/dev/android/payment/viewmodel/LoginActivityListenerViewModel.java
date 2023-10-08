package nuricanozturk.dev.android.payment.viewmodel;

import java.lang.ref.WeakReference;

import nuricanozturk.dev.android.payment.LoginActivity;

public class LoginActivityListenerViewModel
{
    private final WeakReference<LoginActivity> m_loginActivity;

    public LoginActivityListenerViewModel(LoginActivity loginActivity)
    {
        m_loginActivity = new WeakReference<>(loginActivity);
    }

    public void handleLoginButton()
    {
        m_loginActivity.get().loginButtonClicked();
    }

    public void handleCloseButton()
    {
        m_loginActivity.get().finish();
    }
}
