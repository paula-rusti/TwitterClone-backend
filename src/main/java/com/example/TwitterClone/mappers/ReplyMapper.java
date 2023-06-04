package com.example.TwitterClone.mappers;

import com.example.TwitterClone.models.dto.request.CreateReplyRequest;
import com.example.TwitterClone.models.dto.response.ReplyResponse;
import com.example.TwitterClone.models.orm.Reply;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReplyMapper {

    ReplyResponse entityToReplyResponse(Reply reply);

    Reply replyRequestToEntity(CreateReplyRequest request);


}
