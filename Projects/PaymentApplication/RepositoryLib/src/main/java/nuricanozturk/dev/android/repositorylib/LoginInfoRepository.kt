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
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.reflect.Array
import java.util.Optional
import javax.inject.Inject


class LoginInfoRepository @Inject constructor(@ApplicationContext var context: Context): ILoginInfoRepository
{
    private val mContext : Context = context
   /* private fun findByUsernameCallback(fis : FileInputStream, username : String) : List<LoginInfo>
    {
        val loginInfo = ArrayList<LoginInfo>()
        try
        {
            while (true)
            {
                val info = ObjectInputStream(fis).readObject() as? LoginInfo

                if (info?.username == username)
                    loginInfo.add(info)
            }
        }
        catch (ignore : EOFException)
        {

        }
        return loginInfo
    }

    private fun findSuccessByUserNameCallback(fis : FileInputStream, username : String) : List<LoginInfo>
    {
        val loginInfo = ArrayList<LoginInfo>()
        try
        {
            while (true)
            {
                val info = ObjectInputStream(fis).readObject() as? LoginInfo

                if (info?.username == username && info.success)
                    loginInfo.add(info)
            }
        }
        catch (ignore : EOFException)
        {

        }
        return loginInfo
    }
    private fun findFailByUserNameCallback(fis : FileInputStream, username : String) : List<LoginInfo>
    {
        val loginInfo = ArrayList<LoginInfo>()
        try
        {
            while (true)
            {
                val info = ObjectInputStream(fis).readObject() as? LoginInfo

                if (info?.username == username && !info.success)
                    loginInfo.add(info)
            }
        }
        catch (ignore : EOFException)
        {

        }
        return loginInfo
    }*/
   private fun findByUserNameCallback(fis: FileInputStream, username: String): List<LoginInfo>
   {
       val list = ArrayList<LoginInfo>()

       try {
           while (true) {
               val ois = ObjectInputStream(fis)

               val loginInfo = ois.readObject() as LoginInfo

               if (loginInfo.username == username)
                   list.add(loginInfo)
           }
       }
       catch (ignore: EOFException) {

       }

       return list
   }

    private fun findSuccessByUserNameCallback(fis: FileInputStream, username: String): List<LoginInfo>
    {
        val list = ArrayList<LoginInfo>()

        try {
            while (true) {
                val ois = ObjectInputStream(fis)

                val loginInfo = ois.readObject() as LoginInfo

                if (loginInfo.username == username && loginInfo.success)
                    list.add(loginInfo)
            }
        }
        catch (ignore: EOFException) {

        }

        return list
    }

    private fun findFailsByUserNameCallback(fis: FileInputStream, username: String): List<LoginInfo>
    {
        val list = ArrayList<LoginInfo>()

        try {
            while (true) {
                val ois = ObjectInputStream(fis)

                val loginInfo = ois.readObject() as LoginInfo

                if (loginInfo.username == username && !loginInfo.success)
                    list.add(loginInfo)
            }
        }
        catch (ignore: EOFException) {

        }

        return list
    }
    private fun <S : LoginInfo?> saveCallback(fos : FileOutputStream, loginInfo : S) : S
    {
        ObjectOutputStream(fos).writeObject(loginInfo)
        return loginInfo
    }



    override fun <S : LoginInfo?> save(entity : S) : S
    {
        return mContext.openFileOutput(LOGIN_INFO_FILE, Context.MODE_APPEND)
            .use { saveCallback(it, entity) }
    }

    override fun findByUserName(userName : String) : List<LoginInfo>
    {
        return context.openFileInput(LOGIN_INFO_FILE)
            .use { findByUserNameCallback(it, userName) }
    }

    override fun findSuccessByUserName(userName : String) : List<LoginInfo>
    {
        return context.openFileInput(LOGIN_INFO_FILE).use { findSuccessByUserNameCallback(it, userName) }
    }

    override fun findFailsByUserName(userName : String) : List<LoginInfo>
    {
        return context.openFileInput(LOGIN_INFO_FILE)
            .use { findFailsByUserNameCallback(it, userName) }
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