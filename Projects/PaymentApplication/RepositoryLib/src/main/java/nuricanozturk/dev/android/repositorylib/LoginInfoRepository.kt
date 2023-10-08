package nuricanozturk.dev.android.repositorylib

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import nuricanozturk.dev.android.repositorylib.entity.LoginInfo
import nuricanozturk.dev.android.repositorylib.entity.User
import nuricanozturk.dev.android.repositorylib.global.LOGIN_INFO_FILE
import nuricanozturk.dev.android.repositorylib.global.USER_FILE
import java.io.EOFException
import java.io.FileInputStream
import java.io.ObjectInputStream
import java.util.Optional
import javax.inject.Inject


class LoginInfoRepository @Inject constructor(@ApplicationContext var context: Context): ILoginInfoRepository
{
    /*private fun findByUsernameCallback(fis : FileInputStream, username : String) : List<LoginInfo>?
    {
        var loginInfo : List<LoginInfo> = MutableList()
        try
        {
            while (true)
            {
                var info = ObjectInputStream(fis).readObject() as? LoginInfo

                if (info?.username == username)
                    loginInfo.add(info)
            }
        }
        catch (ignore : EOFException)
        {
            user = null
        }
        return user
    }*/
    override fun findByUserName(userName : String) : List<LoginInfo>
    {
       /* return context.openFileInput(LOGIN_INFO_FILE)
            .use { findByUsernameCallback(it, userName) }*/

        TODO()
    }

    override fun findSuccessByUserName(userName : String) : List<LoginInfo>
    {
        TODO("Not yet implemented")
    }

    override fun findFailsByUserName(userName : String) : List<LoginInfo>
    {
        TODO("Not yet implemented")
    }
/////////////////////////////////////////////////////////
    override fun findLastSuccessByUserName(userName : String) : List<LoginInfo>
    {
        TODO("Not yet implemented")
    }

    override fun findLastFailByUserName(userName : String) : List<LoginInfo>
    {
        TODO("Not yet implemented")
    }

    override fun count() : Long
    {
        TODO("Not yet implemented")
    }

    override fun delete(entity : LoginInfo?)
    {
        TODO("Not yet implemented")
    }

    override fun deleteAll()
    {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities : MutableIterable<LoginInfo>?)
    {
        TODO("Not yet implemented")
    }

    override fun findAll() : MutableIterable<LoginInfo>
    {
        TODO("Not yet implemented")
    }

    override fun <S : LoginInfo?> save(entity : S) : S
    {
        TODO("Not yet implemented")
    }

    override fun <S : LoginInfo?> saveAll(entities : MutableIterable<S>?) : MutableIterable<S>
    {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids : MutableIterable<Long>?)
    {
        TODO("Not yet implemented")
    }

    override fun deleteById(id : Long?)
    {
        TODO("Not yet implemented")
    }

    override fun existsById(id : Long?) : Boolean
    {
        TODO("Not yet implemented")
    }

    override fun findAllById(id : MutableIterable<Long>?) : MutableIterable<LoginInfo>
    {
        TODO("Not yet implemented")
    }

    override fun findById(id : Long?) : Optional<LoginInfo>
    {
        TODO("Not yet implemented")
    }
}