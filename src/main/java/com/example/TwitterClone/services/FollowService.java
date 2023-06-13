package com.example.TwitterClone.services;


import com.example.TwitterClone.mappers.FollowMapper;
import com.example.TwitterClone.models.dto.request.FollowRequest;
import com.example.TwitterClone.models.dto.response.FollowResponse;
import com.example.TwitterClone.models.orm.Follow;
import com.example.TwitterClone.repositories.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    private final FollowRepository followRepository;

    private final FollowMapper followMapper;

    @Autowired
    public FollowService(FollowRepository followRepository, FollowMapper followMapper) {
        this.followRepository = followRepository;
        this.followMapper = followMapper;
    }

    public FollowResponse followUser(FollowRequest followRequest) {
        Follow addedFollow = followRepository.save(followMapper.followRequestToEntity(followRequest));
        return followMapper.entityToFollowResponse(addedFollow);
    }

    public void unfollowUser(FollowRequest unfollowRequest) {

        followRepository.delete(followMapper.followRequestToEntity(unfollowRequest));
    }
}
