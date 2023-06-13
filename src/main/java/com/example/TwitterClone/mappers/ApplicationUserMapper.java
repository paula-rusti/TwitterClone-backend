package com.example.TwitterClone.mappers;

import com.example.TwitterClone.models.dto.request.RegistrationRequest;
import com.example.TwitterClone.models.dto.response.UserDetailResponse;
import com.example.TwitterClone.models.orm.ApplicationUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationUserMapper {

    UserDetailResponse entityToUserDetailResponse(ApplicationUser user);

    ApplicationUser userRegisterRequestToEntity(RegistrationRequest request);

}
