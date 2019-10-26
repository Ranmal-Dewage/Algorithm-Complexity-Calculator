/**
 *
 */
package com.sliit.spm.repository;

import com.sliit.spm.model.Analysis;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author tenusha
 *
 */
@Repository
public interface AnalysisRepo extends MongoRepository<Analysis, String> {
    Optional<Analysis> findByProjectKey(String projectKey);

	Optional<List<Analysis>> findFirst10ByOrderByCreatedTimeDesc(String projectKey);

}
