package com.example.TwitterClone.services;

import com.example.TwitterClone.mappers.ReplyMapper;
import com.example.TwitterClone.models.dto.request.CreateReplyRequest;
import com.example.TwitterClone.models.dto.response.ReplyResponse;
import com.example.TwitterClone.models.orm.Reply;
import com.example.TwitterClone.repositories.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    private final ReplyMapper replyMapper;

    @Autowired
    public ReplyService(ReplyRepository replyRepository, ReplyMapper replyMapper) {
        this.replyRepository = replyRepository;
        this.replyMapper = replyMapper;
    }

    public ReplyResponse addReply(CreateReplyRequest request) {
        Reply reply = replyMapper.replyRequestToEntity(request);
        Reply createdReply = replyRepository.save(reply);
        return replyMapper.entityToReplyResponse(createdReply);
    }
}
