package com.musicSearch.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.musicSearch.core.domain.BulletComment;

/**
 * The interface for bullet comment CRUD ops
 * NOTE: extends MongoRepository interface
 * @see MongoRepository 
 * @author ruibi
 *
 */
public interface BulletCommentRepository extends
		MongoRepository<BulletComment, String> {

}
