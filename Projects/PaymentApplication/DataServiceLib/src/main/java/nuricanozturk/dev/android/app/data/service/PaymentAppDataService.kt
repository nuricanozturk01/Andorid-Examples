package nuricanozturk.dev.android.app.data.service

import com.karandev.util.data.repository.exception.RepositoryException
import com.karandev.util.data.service.DataServiceException
import nuricanozturk.dev.android.app.data.service.dto.UserSaveDTO
import nuricanozturk.dev.android.app.data.service.mapper.IUserSaveMapper
import nuricanozturk.dev.android.repositorylib.dal.PaymentApplicationHelper
import javax.inject.Inject

// BRUADA BACKING FIELD'dan DOLAYI CONStrUCtOR INJECTION yAPILAMIYOR
class PaymentAppDataService @Inject constructor(
    paymentHelper : PaymentApplicationHelper,
    userSaveMapper : IUserSaveMapper)
{
    private val mPaymentHelper = paymentHelper
    private val mUserSaveMapper = userSaveMapper

    fun saveUser(userSaveDTO: UserSaveDTO) : Boolean
    {
        var result = false
        try
        {
            mPaymentHelper.saveUser(mUserSaveMapper.toUser(userSaveDTO))
            result = true
        }
        catch (ex: RepositoryException)
        {
            throw DataServiceException("PaymentAppDataService.saveUser", ex.cause)
        }
        catch (ex: Throwable)
        {
            throw DataServiceException("PaymentAppDataService.saveUser", ex)
        }

        return result
    }
}