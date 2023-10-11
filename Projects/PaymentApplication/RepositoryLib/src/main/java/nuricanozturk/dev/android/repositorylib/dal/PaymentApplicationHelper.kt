package nuricanozturk.dev.android.repositorylib.dal

import com.karandev.util.data.repository.exception.RepositoryException
import nuricanozturk.dev.android.repositorylib.ILoginInfoRepository
import nuricanozturk.dev.android.repositorylib.IPaymentRepository
import nuricanozturk.dev.android.repositorylib.IUserRepository
import nuricanozturk.dev.android.repositorylib.entity.LoginInfo
import nuricanozturk.dev.android.repositorylib.entity.User
import javax.inject.Inject

class PaymentApplicationHelper @Inject constructor()
{
    @Inject
    lateinit var userRepository : IUserRepository

    @Inject
    lateinit var loginInfoRepository : ILoginInfoRepository

    @Inject
    lateinit var paymentRepository : IPaymentRepository


    fun saveUser(user : User) : User
    {
        try
        {
            return userRepository.save(user)
        }
        catch (ex : Throwable)
        {
            throw RepositoryException("PaymentApplicationHelper.saveUser", ex)
        }
    }
    fun saveLoginInfo(loginInfo : LoginInfo) : LoginInfo
    {
        try
        {
            return loginInfoRepository.save(loginInfo)
        }
        catch (ex : Throwable)
        {
            throw RepositoryException("PaymentApplicationHelper.saveLoginInfo", ex)
        }
    }
    fun findByUserNameAndPassword(userName : String, password : String) : User?
    {
        try
        {
            return userRepository.findByUserNameAndPassword(userName, password)
        }
        catch (ex : Throwable)
        {
            throw RepositoryException("PaymentApplicationHelper.findByUserNameAndPassword", ex)
        }
    }

    fun existsByUserNameAndPassword(userName : String, password : String) : Boolean
    {
        try
        {
            return userRepository.existsByUsernameAndPassword(userName, password)
        }
        catch (ex : Throwable)
        {
            throw RepositoryException("PaymentApplicationHelper.existsByUserNameAndPassword", ex)
        }
    }


    fun existsByUsername(username : String?) : Boolean
    {
        try
        {
            return userRepository.existsById(username)
        }
        catch (ex : Throwable)
        {
            throw RepositoryException("PaymentApplicationHelper.existsById", ex)
        }
    }


    fun findLoginInfoByUserName(userName : String) : List<LoginInfo>
    {
        try
        {
            return loginInfoRepository.findByUserName(userName)
        }
        catch (ex : Throwable)
        {
            throw RepositoryException("PaymentApplicationHelper.findLoginInfoByUserName", ex)
        }
    }


    fun findSuccessByUserNameLoginInfo(userName : String) : List<LoginInfo>
    {
        try
        {
            return loginInfoRepository.findSuccessByUserName(userName)
        }
        catch (ex : Throwable)
        {
            throw RepositoryException("PaymentApplicationHelper.findSuccessByUserNameLoginInfo", ex)
        }
    }

    fun findFailsByUserNameLoginInfo(userName : String) : List<LoginInfo>
    {
        try
        {
            return loginInfoRepository.findFailsByUserName(userName)
        }
        catch (ex : Throwable)
        {
            throw RepositoryException("PaymentApplicationHelper.findFailsByUserNameLoginInfo", ex)
        }
    }

}