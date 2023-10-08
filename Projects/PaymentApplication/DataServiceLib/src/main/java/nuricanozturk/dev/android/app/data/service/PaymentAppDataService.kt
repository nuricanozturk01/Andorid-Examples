package nuricanozturk.dev.android.app.data.service

import com.karandev.util.data.repository.exception.RepositoryException
import com.karandev.util.data.service.DataServiceException
import nuricanozturk.dev.android.app.data.service.dto.LoginInfoDTO
import nuricanozturk.dev.android.app.data.service.dto.UserSaveDTO
import nuricanozturk.dev.android.app.data.service.mapper.ILoginInfoMapper
import nuricanozturk.dev.android.app.data.service.mapper.IUserSaveMapper
import nuricanozturk.dev.android.app.data.service.mapper.di.module.annotation.LoginInfoMapperInterceptor
import nuricanozturk.dev.android.app.data.service.mapper.di.module.annotation.UserSaveMapperInterceptor
import nuricanozturk.dev.android.repositorylib.dal.PaymentApplicationHelper
import nuricanozturk.dev.android.repositorylib.entity.LoginInfo
import javax.inject.Inject
import kotlin.math.log

// BRUADA BACKING FIELD'dan DOLAYI CONStrUCtOR INJECTION yAPILAMIYOR
class PaymentAppDataService @Inject constructor(paymentHelper : PaymentApplicationHelper,
                                                @UserSaveMapperInterceptor userSaveMapper : IUserSaveMapper,
                                                @LoginInfoMapperInterceptor loginInfoMapper : ILoginInfoMapper)
{
    private val mPaymentHelper = paymentHelper


    private val mUserSaveMapper = userSaveMapper

    private val mLoginInfoMapper = loginInfoMapper

    fun saveUser(userSaveDTO : UserSaveDTO) : Boolean
    {
        var result = false
        try
        {
            mPaymentHelper.saveUser(mUserSaveMapper.toUser(userSaveDTO))
            result = true
        }
        catch (ex : RepositoryException)
        {
            throw DataServiceException("PaymentAppDataService.saveUser", ex.cause)
        }
        catch (ex : Throwable)
        {
            throw DataServiceException("PaymentAppDataService.saveUser", ex)
        }

        return result
    }


    fun checkAndSaveLoginInfo(loginInfoDTO : LoginInfoDTO) : Boolean
    {
        try
        {
            if (!mPaymentHelper.existsByUsername(loginInfoDTO.username)) return false

            val loginInfo = mLoginInfoMapper.toLoginInfo(loginInfoDTO)

            if (mPaymentHelper.existsByUserNameAndPassword(loginInfoDTO.username, loginInfoDTO.password))
                mPaymentHelper.saveLoginInfo(loginInfo)

            else mPaymentHelper.saveLoginInfo(loginInfo.also { it.success = false })

            return loginInfo.success
        }
        catch (ex : RepositoryException)
        {
            throw DataServiceException("PaymentAppDataService.saveUser", ex.cause)
        }
        catch (ex : Throwable)
        {
            throw DataServiceException("PaymentAppDataService.saveUser", ex)
        }
    }
}