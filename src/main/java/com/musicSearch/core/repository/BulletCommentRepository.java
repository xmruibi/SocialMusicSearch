package com.musicSearch.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.musicSearch.core.domain.BulletComment;

public interface BulletCommentRepository extends
		MongoRepository<BulletComment, String> {

}
