package com.example.TwitterClone.services;


import com.example.TwitterClone.models.dto.request.FollowRequest;
import com.example.TwitterClone.models.orm.Follow;
import com.example.TwitterClone.repositories.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    private final FollowRepository followRepository;

    @Autowired
    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public Follow followUser(Long followerId, FollowRequest followRequest) {
        // followerId represent the id of the logged in user and will later be extracted from
        // an auth header eventually
        return followRepository.save(new Follow(followRequest.getUserId(), followerId));
    }

    public void unfollowUser(Long followerId, FollowRequest followRequest) {
        // or i should separately retrieve the row and then delete by id
        // shall use transactional
        followRepository.delete(new Follow(followRequest.getUserId(), followerId));
    }
}
