package nuricanozturk.dev.android.repositorylib.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nuricanozturk.dev.android.repositorylib.IUserRepository
import nuricanozturk.dev.android.repositorylib.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepository: UserRepository) : IUserRepository
}