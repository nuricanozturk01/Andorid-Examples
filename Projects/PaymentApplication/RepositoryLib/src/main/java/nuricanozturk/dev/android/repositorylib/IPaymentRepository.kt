package nuricanozturk.dev.android.repositorylib

import com.karandev.util.data.repository.ICrudRepository
import nuricanozturk.dev.android.repositorylib.entity.Payment

interface IPaymentRepository : ICrudRepository<Payment, Long>
{
    fun findByUserName(userName : String) : List<Payment>
}