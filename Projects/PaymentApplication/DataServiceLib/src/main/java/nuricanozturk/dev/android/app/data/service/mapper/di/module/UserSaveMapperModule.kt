package nuricanozturk.dev.android.app.data.service.mapper.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nuricanozturk.dev.android.app.data.service.mapper.IUserSaveMapper
import nuricanozturk.dev.android.app.data.service.mapper.UserSaveMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserSaveMapperModule // Nasıl yaratacağımızı vermeyeceksek abstract class
{
    @Binds
    @Singleton
    abstract fun bindUserMapper(userMapper: UserSaveMapper) : IUserSaveMapper
}