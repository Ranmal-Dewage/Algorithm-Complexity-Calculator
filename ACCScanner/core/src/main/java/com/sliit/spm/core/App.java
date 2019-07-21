package com.sliit.spm.core;

import com.sliit.spm.model.Project;

public class App {

    static void execute(Project project) {

        System.out.println(project.getProjectKey());
        System.out.println(project.getSourcePath());

    }

    /**
     * java -DprojectKey=ACC -DsourcePath=E:\Development\algorithemic-complexity-calculator -jar core-1.0-SNAPSHOT-jar-with-dependencies.jar
     * @param args
     */
    public static void main(String[] args) {

        String projectKey = System.getProperty("projectKey");
        String sourcePath = System.getProperty("sourcePath");

        Project project = new Project();
        project.setProjectKey(projectKey);
        project.setSourcePath(sourcePath);

        execute(project);

    }
}
