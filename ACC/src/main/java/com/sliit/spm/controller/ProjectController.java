/**
 * 
 */
package com.sliit.spm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sliit.spm.model.Project;
import com.sliit.spm.service.ProjectService;

/**
 * @author it16166752
 *
 */
@RestController
public class ProjectController {
	@Autowired
	ProjectService projectService;

	@PostMapping("/projects")
	public ResponseEntity<?> saveProject(@RequestBody Project project) {
		return new ResponseEntity<>(projectService.save(project), HttpStatus.OK);
	}

	@GetMapping("/projects/{key}")
	public ResponseEntity<?> getProject(@PathVariable("key") String key) {
		return new ResponseEntity<>(projectService.getByKey(key), HttpStatus.OK);
	}

	@GetMapping("/projects/{key}/history")
	public ResponseEntity<?> getProjectHistory(@PathVariable("key") String key) {
		return new ResponseEntity<>(projectService.getHistoryByKey(key), HttpStatus.OK);
	}

	@GetMapping("/projects")
	public ResponseEntity<?> getProjects() {
		return new ResponseEntity<>(projectService.getAll(), HttpStatus.OK);
	}
}
