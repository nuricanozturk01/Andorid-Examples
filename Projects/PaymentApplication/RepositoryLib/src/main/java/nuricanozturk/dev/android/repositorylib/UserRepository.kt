package nuricanozturk.dev.android.repositorylib

import android.content.Context
import android.content.Context.MODE_APPEND
import dagger.hilt.android.qualifiers.ApplicationContext
import nuricanozturk.dev.android.repositorylib.entity.User
import nuricanozturk.dev.android.repositorylib.global.USER_FILE
import java.io.EOFException
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.Optional
import javax.inject.Inject

class UserRepository @Inject constructor(@ApplicationContext var context : Context) :
    IUserRepository
{

    private val mContext : Context = context
    private fun <S : User?> saveCallback(fos : FileOutputStream, user : S) : S
    {
        ObjectOutputStream(fos).writeObject(user)
        return user
    }

    private fun findByUsernameAndPasswordCallback(fis : FileInputStream,
                                                  username : String,
                                                  password : String) : User?
    {
        var user : User? = null
        try
        {
            while (true)
            {
                user = ObjectInputStream(fis).readObject() as? User

                if (user?.username == username && user.password == password) break
            }
        }
        catch (ignore : EOFException)
        {
            user = null
        }
        return user
    }

    private fun existsByUsernameCallback(fis : FileInputStream, username : String) : Boolean
    {
        var user : User? = null
        try
        {
            while (true)
            {
                user = ObjectInputStream(fis).readObject() as? User

                if (user?.username == username) break
            }
        }
        catch (ignore : EOFException)
        {
            user = null
        }
        return user != null
    }


    override fun <S : User?> save(entity : S) : S
    {
        return mContext.openFileOutput(USER_FILE, MODE_APPEND).use { saveCallback(it, entity) }
    }

    override fun findByUserNameAndPassword(userName : String, password : String) : User?
    {
        var result : User? = null
        try
        {
            result = mContext.openFileInput(USER_FILE)
                .use { findByUsernameAndPasswordCallback(it, userName, password) }
        }
        catch (ignore : FileNotFoundException)
        {

        }
        return result
    }


    override fun existsById(username : String?) : Boolean
    {

        var result = false
        try
        {
            result =
                mContext.openFileInput(USER_FILE).use { existsByUsernameCallback(it, username!!) }
        }
        catch (ignore : FileNotFoundException)
        {

        }
        return result
    }

    override fun count() : Long
    {
        TODO("Not yet implemented")
    }

    override fun delete(entity : User?)
    {
        TODO("Not yet implemented")
    }

    override fun deleteAll()
    {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities : MutableIterable<User>?)
    {
        TODO("Not yet implemented")
    }

    override fun findAll() : MutableIterable<User>
    {
        TODO("Not yet implemented")
    }


    override fun <S : User?> saveAll(entities : MutableIterable<S>?) : MutableIterable<S>
    {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids : MutableIterable<String>?)
    {
        TODO("Not yet implemented")
    }

    override fun deleteById(id : String?)
    {
        TODO("Not yet implemented")
    }


    override fun findAllById(id : MutableIterable<String>?) : MutableIterable<User>
    {
        TODO("Not yet implemented")
    }

    override fun findById(id : String?) : Optional<User>
    {
        TODO("Not yet implemented")
    }
}