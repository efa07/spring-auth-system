package com.springauth.efa.service;

import com.springauth.efa.entity.UserEntity;
import com.springauth.efa.io.ProfileRequest;
import com.springauth.efa.io.ProfileResponse;
import com.springauth.efa.repostory.UserRepostory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceimpl implements ProfileService {

    private final UserRepostory userRepostory;

    @Override
    public ProfileResponse createProfile(ProfileRequest request){
        UserEntity newProfile = convertToUserEntity(request);
        newProfile = userRepostory.save(newProfile);
        return convertToProfileResponse(newProfile);
    }

    private ProfileResponse convertToProfileResponse(UserEntity newProfile) {
    return ProfileResponse.builder()
            .name(newProfile.getName())
            .email(newProfile.getEmail())
            .userId(newProfile.getUserId())
            .isAccountVerified(String.valueOf(newProfile.getIsAccountVerfied()))
            .build();
    }

    private UserEntity convertToUserEntity(ProfileRequest request) {
    return UserEntity.builder()
            .email(request.getEmail())
            .userId(UUID.randomUUID().toString())
            .name(request.getName())
            .password(request.getPassword())
            .isAccountVerfied(false)
            .resetOtpExpiredAt(0L)
            .verfiyOtp(null)
            .verifyOtpExpireAt(0L)
            .resetOtp(null)
            .build();
    }
}
