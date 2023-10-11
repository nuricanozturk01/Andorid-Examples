package nuricanozturk.dev.android.app.data.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import nuricanozturk.dev.android.app.data.service.dto.LoginInfoDTO;
import nuricanozturk.dev.android.app.data.service.dto.LoginInfoStatusDTO;
import nuricanozturk.dev.android.repositorylib.entity.LoginInfo;

@Mapper(implementationName = "LoginInfoMapperImpl")
public interface ILoginInfoMapper
{
    LoginInfo toLoginInfo(LoginInfoDTO dto);
    //LoginInfoDTO toLoginInfoDTO(LoginInfo loginInfo);

    @Mapping(source = "loginDateTime", target = "loginDateTimeStr", dateFormat = "dd/MM/yyyy kk:mm:ss")
    LoginInfoStatusDTO toLoginInfoStatusDTO(LoginInfo loginInfo);
}
