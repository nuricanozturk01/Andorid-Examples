package nuricanozturk.dev.android.repositorylib.dal

import nuricanozturk.dev.android.repositorylib.ILoginInfoRepository
import nuricanozturk.dev.android.repositorylib.IPaymentRepository
import nuricanozturk.dev.android.repositorylib.IUserRepository
import javax.inject.Inject

class PaymentApplicationHelper @Inject constructor()
{
    @Inject
    lateinit var userRepository: IUserRepository

    @Inject
    lateinit var loginInfoRepository: ILoginInfoRepository

    @Inject
    lateinit var paymentRepository: IPaymentRepository
}