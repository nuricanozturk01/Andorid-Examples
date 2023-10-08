package nuricanozturk.dev.android.app.data.service.mapper;

import org.mapstruct.Mapper;

import nuricanozturk.dev.android.app.data.service.dto.LoginInfoDTO;
import nuricanozturk.dev.android.repositorylib.entity.LoginInfo;

@Mapper(implementationName = "LoginInfoMapperImpl")
public interface ILoginInfoMapper
{
    LoginInfo toLoginInfo(LoginInfoDTO dto);
}
