package com.sliit.spm.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Document(collection = "projects")
public class Projects {

    @Id
    private String id;
    @Indexed
    String projectKey;
    List<Project> projects;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project) {
        if (Objects.isNull(projects)) {
            projects = Arrays.asList(project);
        } else {
            projects.add(project);
        }
    }

    @Override
    public String toString() {
        return "Projects{" +
                "key='" + projectKey + '\'' +
                ", project=" + projects +
                '}';
    }
}
