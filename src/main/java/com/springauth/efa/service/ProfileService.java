package com.springauth.efa.service;

import com.springauth.efa.io.ProfileRequest;
import com.springauth.efa.io.ProfileResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProfileService {
ProfileResponse createProfile(ProfileRequest request);
}
