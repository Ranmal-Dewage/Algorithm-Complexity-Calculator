/**
 * 
 */
package com.sliit.spm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sliit.spm.model.Project;
import com.sliit.spm.repository.ProjectRepo;
import com.sliit.spm.service.ProjectService;

/**
 * @author it16166752
 *
 */
@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	ProjectRepo projectRepo;

	public Project save(Project project) {
		return projectRepo.save(project);
	}

	public Optional<Project> getByKey(String projectKey) {
		return projectRepo.findByProjectKey(projectKey);
	}

	public List<Project> getAll() {
		return projectRepo.findAll();
	}
}
