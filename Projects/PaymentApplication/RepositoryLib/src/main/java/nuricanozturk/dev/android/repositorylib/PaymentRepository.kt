package nuricanozturk.dev.android.repositorylib

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import nuricanozturk.dev.android.repositorylib.entity.LoginInfo
import nuricanozturk.dev.android.repositorylib.entity.Payment
import nuricanozturk.dev.android.repositorylib.global.LOGIN_INFO_FILE
import nuricanozturk.dev.android.repositorylib.global.PAYMENT_FILE
import java.io.EOFException
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.Optional
import javax.inject.Inject

class PaymentRepository @Inject constructor(@ApplicationContext var context : Context) :
    IPaymentRepository
{
    override fun <S : Payment?> save(entity : S) : S
    {
        return context.openFileOutput(PAYMENT_FILE, Context.MODE_APPEND).use { saveCallback(it, entity) }
    }
    private fun findByUserNameCallback(fis: FileInputStream, username: String): List<Payment>
    {
        val list = ArrayList<Payment>()

        try {
            while (true) {
                val ois = ObjectInputStream(fis)

                val paymentInfo = ois.readObject() as Payment

                if (paymentInfo.username == username)
                    list.add(paymentInfo)
            }
        }
        catch (ignore: EOFException) {

        }

        return list
    }
    private fun <S : Payment?> saveCallback(fos : FileOutputStream, payment : S) : S
    {
        ObjectOutputStream(fos).writeObject(payment)
        return payment
    }

    ///////////////////////
    override fun findByUserName(userName : String) : List<Payment>
    {
        return context.openFileInput(PAYMENT_FILE)
            .use { findByUserNameCallback(it, userName) }
    }

    override fun count() : Long
    {
        TODO("Not yet implemented")
    }

    override fun delete(entity : Payment?)
    {
        TODO("Not yet implemented")
    }

    override fun deleteAll()
    {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities : MutableIterable<Payment>?)
    {
        TODO("Not yet implemented")
    }

    override fun findAll() : MutableIterable<Payment>
    {
        TODO("Not yet implemented")
    }


    override fun <S : Payment?> saveAll(entities : MutableIterable<S>?) : MutableIterable<S>
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

    override fun findAllById(id : MutableIterable<Long>?) : MutableIterable<Payment>
    {
        TODO("Not yet implemented")
    }

    override fun findById(id : Long?) : Optional<Payment>
    {
        TODO("Not yet implemented")
    }
}