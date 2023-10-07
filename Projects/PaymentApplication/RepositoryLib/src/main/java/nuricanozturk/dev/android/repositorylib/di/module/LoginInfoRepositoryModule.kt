package nuricanozturk.dev.android.repositorylib.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nuricanozturk.dev.android.repositorylib.ILoginInfoRepository
import nuricanozturk.dev.android.repositorylib.IUserRepository
import nuricanozturk.dev.android.repositorylib.LoginInfoRepository
import nuricanozturk.dev.android.repositorylib.PaymentRepository
import nuricanozturk.dev.android.repositorylib.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginInfoRepositoryModule
{
    @Binds
    @Singleton
    abstract fun bindUserRepository(loginInfoRepository : LoginInfoRepository) : ILoginInfoRepository
}