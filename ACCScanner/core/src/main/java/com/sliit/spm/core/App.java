package com.sliit.spm.core;

import com.sliit.spm.model.Project;
import com.sliit.spm.utils.ErrorMessages;
import com.sun.javafx.fxml.PropertyNotFoundException;
import org.apache.log4j.Logger;

import java.util.Optional;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class);

    static void execute(Project project) {

        LOGGER.info("Analyzing project " + project.getProjectKey() + "...");
        LOGGER.info("Found source path " + project.getSourcePath());

    }

    /**
     * java -DprojectKey=ACC -DsourcePath=E:\Development\algorithemic-complexity-calculator -jar core-1.0-SNAPSHOT-jar-with-dependencies.jar
     *
     * @param args
     */
    public static void main(String[] args) {

        org.apache.log4j.PropertyConfigurator.configure("./config/log4j.properties");
        LOGGER.debug("ACCScanner start analyzing...");

        Optional<String> projectKey = Optional.ofNullable(System.getProperty("projectKey"));
        Optional<String> sourcePath = Optional.ofNullable(System.getProperty("sourcePath"));

        Project project = new Project();
        if (projectKey.isPresent()) {
            project.setProjectKey(projectKey.get());
        } else {
            LOGGER.error(ErrorMessages.PK_NOT_FOUND_ERR);
            throw new PropertyNotFoundException(ErrorMessages.PK_NOT_FOUND_ERR);
        }
        if (sourcePath.isPresent()) {
            project.setSourcePath(sourcePath.get());
        } else {
            LOGGER.error(ErrorMessages.SP_NOT_FOUND_ERR);
            throw new PropertyNotFoundException(ErrorMessages.SP_NOT_FOUND_ERR);
        }

        execute(project);

    }
}
