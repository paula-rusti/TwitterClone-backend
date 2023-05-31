package com.example.TwitterClone.mappers;

import com.example.TwitterClone.models.dto.request.CreatePostRequest;
import com.example.TwitterClone.models.dto.response.PostResponse;
import com.example.TwitterClone.models.orm.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "ownerId", source = "owner.id")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "created", source = "created", dateFormat = "yyyy-MM-dd HH:mm:ss")
    PostResponse entityToPostResponse(Post post);

    @Mapping(target = "ownerId", source = "owner.id")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "created", source = "created", dateFormat = "yyyy-MM-dd HH:mm:ss")
    Post postRequestToEntity(CreatePostRequest request);
}
