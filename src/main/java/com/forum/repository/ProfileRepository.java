package com.forum.repository;

import org.springframework.data.jpa.repository.Query;

import com.forum.domain.Profile;

public interface ProfileRepository extends AbstractRepository <Profile> {
	@Query("SELECT * FROM PROFILE WHERE user_id = ?")
	Profile findByUserId(long userId);
}
