package nuricanozturk.dev.android.app.data.service.mapper

import nuricanozturk.dev.android.app.data.service.dto.UserSaveDTO
import nuricanozturk.dev.android.repositorylib.entity.User
import java.time.LocalDate
import javax.inject.Inject

class UserSaveMapper @Inject constructor(): IUserSaveMapper
{
    override fun toUser(userSaveDTO : UserSaveDTO) : User
    {
        return User(userSaveDTO.username, userSaveDTO.password, userSaveDTO.firstName, userSaveDTO.middleName,
            userSaveDTO.lastName, userSaveDTO.birthDate, LocalDate.now())
    }

    override fun toUserSaveDTO(user : User) : UserSaveDTO
    {
        return UserSaveDTO(user.username, user.password, user.firstName,
            user.lastName, user.birthDate, user.middleName)
    }
}