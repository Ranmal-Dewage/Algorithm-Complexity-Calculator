/**
 *
 */
package com.sliit.spm.service;

import com.sliit.spm.model.Analysis;
import com.sliit.spm.model.Project;

import java.util.List;
import java.util.Optional;

/**
 * @author it16166752
 *
 */
public interface ProjectService {
	public Project save(Project project);

	public Optional<Project> getByKey(String projectKey);

	public List<Project> getAll();

	public List<Analysis> getHistoryByKey(String key);
}
