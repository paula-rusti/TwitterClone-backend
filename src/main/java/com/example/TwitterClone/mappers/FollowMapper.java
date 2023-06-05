package com.example.TwitterClone.mappers;


import com.example.TwitterClone.models.dto.request.FollowRequest;
import com.example.TwitterClone.models.dto.response.FollowResponse;
import com.example.TwitterClone.models.orm.Follow;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FollowMapper {

    FollowResponse entityToFollowResponse(Follow follow);

    Follow followRequestToEntity(FollowRequest request);

}
