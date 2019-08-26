/**
 * 
 */
package com.sliit.spm.repository;

import com.sliit.spm.model.Projects;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author tenusha
 *
 */
@Repository
public interface ProjectsRepo extends MongoRepository<Projects, String> {
	public Optional<Projects> findByProjectKey(String projectKey);
}
