package nuricanozturk.dev.android.repositorylib

import com.karandev.util.data.repository.ICrudRepository
import nuricanozturk.dev.android.repositorylib.entity.User

interface IUserRepository : ICrudRepository<User, String>
{
    fun existsByUsernameAndPassword(username : String, password : String) = findByUserNameAndPassword(username, password) != null
    fun findByUserNameAndPassword(userName: String, password: String) : User?
}