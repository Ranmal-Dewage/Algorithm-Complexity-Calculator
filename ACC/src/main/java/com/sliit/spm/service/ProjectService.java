/**
 * 
 */
package com.sliit.spm.service;

import java.util.List;
import java.util.Optional;

import com.sliit.spm.model.Project;

/**
 * @author it16166752
 *
 */
public interface ProjectService {
	public Project save(Project project);

	public Optional<Project> getByKey(String projectKey);

	public List<Project> getAll();
}
