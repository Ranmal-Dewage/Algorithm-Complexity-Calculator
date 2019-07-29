/**
 * 
 */
package com.sliit.spm.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sliit.spm.model.Project;
import com.sliit.spm.repository.ProjectRepo;
import com.sliit.spm.service.ProjectService;

//import static org.springframework.data.domain.Sort;
/**
 * @author it16166752
 *
 */
@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	ProjectRepo projectRepo;

	@Override
	public Project save(Project project) {
		project.setCreatedTime(LocalDateTime.now());
		return projectRepo.save(project);
	}

	@Override
	public Optional<Project> getByKey(String projectKey) {
		return projectRepo.findByProjectKey(projectKey);
	}

	@Override
	public List<Project> getAll() {
		return projectRepo.findAll();
	}

	public List<Project> getAllByCreatedTime() {
		return projectRepo.findByOrderByCreatedTimeAsc().stream().limit(10).collect(Collectors.toList());
	}
}
