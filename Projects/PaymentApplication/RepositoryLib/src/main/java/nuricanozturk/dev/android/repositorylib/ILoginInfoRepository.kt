package nuricanozturk.dev.android.repositorylib

import com.karandev.util.data.repository.ICrudRepository
import nuricanozturk.dev.android.repositorylib.entity.LoginInfo

interface ILoginInfoRepository : ICrudRepository<LoginInfo, Long>
{
    fun findByUserName(userName: String) : List<LoginInfo>
    fun findSuccessByUserName(userName: String) : List<LoginInfo>
    fun findFailsByUserName(userName: String) : List<LoginInfo>
    fun findLastSuccessByUserName(userName: String) : List<LoginInfo>
    fun findLastFailByUserName(userName: String) : List<LoginInfo>
}