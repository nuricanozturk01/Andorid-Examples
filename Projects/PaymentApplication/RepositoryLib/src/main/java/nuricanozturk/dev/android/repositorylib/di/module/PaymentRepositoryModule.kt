package nuricanozturk.dev.android.repositorylib.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nuricanozturk.dev.android.repositorylib.IPaymentRepository
import nuricanozturk.dev.android.repositorylib.PaymentRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PaymentRepositoryModule
{
    @Binds
    @Singleton
    abstract fun bindUserRepository(paymentRepository : PaymentRepository) : IPaymentRepository
}