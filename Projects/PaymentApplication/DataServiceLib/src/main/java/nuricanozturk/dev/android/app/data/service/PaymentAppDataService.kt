package nuricanozturk.dev.android.app.data.service

import com.karandev.util.data.repository.exception.RepositoryException
import com.karandev.util.data.service.DataServiceException
import nuricanozturk.dev.android.app.data.service.dto.LoginInfoDTO
import nuricanozturk.dev.android.app.data.service.dto.LoginInfoStatusDTO
import nuricanozturk.dev.android.app.data.service.dto.PaymentSaveDTO
import nuricanozturk.dev.android.app.data.service.dto.UserSaveDTO
import nuricanozturk.dev.android.app.data.service.mapper.ILoginInfoMapper
import nuricanozturk.dev.android.app.data.service.mapper.IPaymentSaveMapper
import nuricanozturk.dev.android.app.data.service.mapper.IUserSaveMapper
import nuricanozturk.dev.android.repositorylib.dal.PaymentApplicationHelper
import javax.inject.Inject

// BRUADA BACKING FIELD'dan DOLAYI CONStrUCtOR INJECTION yAPILAMIYOR
class PaymentAppDataService @Inject constructor(paymentHelper : PaymentApplicationHelper,
                                                userSaveMapper : IUserSaveMapper,
                                                paymentSaveMapper : IPaymentSaveMapper,
                                                loginInfoMapper : ILoginInfoMapper)
{
    private val mPaymentHelper = paymentHelper
    private val mUserSaveMapper = userSaveMapper
    private val mLoginInfoMapper = loginInfoMapper
    private val mPaymentSaveMapper = paymentSaveMapper

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

    fun makePayment(paymentSaveDTO : PaymentSaveDTO) : Boolean
    {
        var result = false
        try
        {
            mPaymentHelper.makePayment(mPaymentSaveMapper.toPayment(paymentSaveDTO))
            result = true
        }
        catch (ex : RepositoryException)
        {
            throw DataServiceException("PaymentAppDataService.makePayment", ex.cause)
        }
        catch (ex : Throwable)
        {
            throw DataServiceException("PaymentAppDataService.makePayment", ex)
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

    fun findLoginInfoByUsername(username: String) : List<LoginInfoStatusDTO>
    {
        try{
            return mPaymentHelper.findLoginInfoByUserName(username)
                .map { mLoginInfoMapper.toLoginInfoStatusDTO(it) }
                .toList()
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
    fun findSuccessLoginByUsername(username: String) : List<LoginInfoStatusDTO>
    {
        try{

            return mPaymentHelper.findSuccessByUserNameLoginInfo(username)
                .map { mLoginInfoMapper.toLoginInfoStatusDTO(it) }
                .toList()
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

    fun findFailLoginByUsername(username: String) : List<LoginInfoStatusDTO>
    {
        try{
            return mPaymentHelper.findFailsByUserNameLoginInfo(username)
                .map { mLoginInfoMapper.toLoginInfoStatusDTO(it) }
                .toList()

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