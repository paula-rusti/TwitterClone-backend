package com.example.TwitterClone.mappers;

import com.example.TwitterClone.models.dto.request.CreatePostRequest;
import com.example.TwitterClone.models.dto.request.CreateReactionRequest;
import com.example.TwitterClone.models.dto.response.PostResponse;
import com.example.TwitterClone.models.orm.Post;
import com.example.TwitterClone.models.orm.Reaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostResponse entityToPostResponse(Post post);

    Post postRequestToEntity(CreatePostRequest request);

    Reaction reactionRequestToEntity(CreateReactionRequest request);

    CreateReactionRequest reactionEntityToReactionRequest(Reaction reaction);
}
