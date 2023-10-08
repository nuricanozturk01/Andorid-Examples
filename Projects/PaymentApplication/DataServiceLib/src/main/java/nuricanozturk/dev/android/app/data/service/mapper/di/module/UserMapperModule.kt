package nuricanozturk.dev.android.app.data.service.mapper.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nuricanozturk.dev.android.app.data.service.mapper.IUserSaveMapper
import nuricanozturk.dev.android.app.data.service.mapper.di.module.annotation.UserSaveMapperInterceptor
import org.mapstruct.factory.Mappers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserMapperModule
{
    @Provides
    @Singleton
    @UserSaveMapperInterceptor
    fun provideUserMapper() : IUserSaveMapper = Mappers.getMapper(IUserSaveMapper::class.java)
}