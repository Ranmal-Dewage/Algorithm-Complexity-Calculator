/**
 * 
 */
package com.sliit.spm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sliit.spm.model.Project;

/**
 * @author it16166752
 *
 */
@Repository
public interface ProjectRepo extends MongoRepository<Project, String> {

	//public List<Project> findByOrderByCreatedTimeAsc();
}
