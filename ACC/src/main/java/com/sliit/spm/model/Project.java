package com.sliit.spm.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


public class Project {
	@Id
	private String id;
	@Indexed
	String projectKey;
	String sourcePath;
	String language;
	List<ProjectFile> files;
	@Indexed
	private LocalDateTime createdTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ProjectFile> getFiles() {
		return files;
	}

	public void setFiles(List<ProjectFile> files) {
		this.files = files;
	}

	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public String toString() {
		return "Project{" + "projectKey='" + projectKey + '\'' + ", sourcePath='" + sourcePath + '\'' + ", language='"
				+ language + '\'' + ", files=" + files + '}';
	}
}
