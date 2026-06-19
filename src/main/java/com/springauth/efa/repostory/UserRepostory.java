package com.springauth.efa.repostory;

import com.springauth.efa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepostory extends JpaRepository<UserEntity, Long> {
Optional<UserEntity> findByEmail(String email);
}
