/**
 *
 */
package com.sliit.spm.service.impl;

import com.sliit.spm.model.Analysis;
import com.sliit.spm.model.Project;
import com.sliit.spm.repository.AnalysisRepo;
import com.sliit.spm.repository.ProjectRepo;
import com.sliit.spm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author it16166752
 *
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepo projectRepo;

    @Autowired
    AnalysisRepo analysisRepo;

    @Override
    public Project save(Project project) {

        Analysis analysis = new Analysis();
        analysis.setCreatedTime(Instant.now().getEpochSecond());
        analysis.setProject(project);
        analysis.setProjectKey(project.getProjectKey());
        analysisRepo.save(analysis);

        return projectRepo.save(project);
    }

    @Override
    public Optional<Project> getByKey(String projectKey) {
        return projectRepo.findById(projectKey);
    }

    @Override
    public List<Project> getAll() {
        return projectRepo.findAll();
    }



    @Override
    public List<Analysis> getHistoryByKey(String key) {
//        return analysisRepo.findFirst10ByOrderByCreatedTimeDesc(key);
        List<Analysis> li = analysisRepo.findAll();
        if(li.size() > 0) {
        	List<Analysis> al = new ArrayList<>();
        	for(Analysis a : li) {
        		if(a.getProjectKey().equals(key)) {
        			al.add(a);
        		}
        	}
        	return al;
        }
        return null;
    }
}
