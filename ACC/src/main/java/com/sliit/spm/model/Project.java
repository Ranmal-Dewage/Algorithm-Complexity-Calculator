package com.sliit.spm.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

public class Project {
	@Id
	@Indexed
	String projectKey;
	String sourcePath;
	String language;
	List<ProjectFile> files;

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

	@Override
	public String toString() {
		return "Project{" +
				"projectKey='" + projectKey + '\'' +
				", sourcePath='" + sourcePath + '\'' +
				", language='" + language + '\'' +
				", files=" + files +
				'}';
	}
}
