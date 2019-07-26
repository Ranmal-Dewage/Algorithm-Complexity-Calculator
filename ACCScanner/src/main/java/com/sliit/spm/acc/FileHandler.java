package com.sliit.spm.acc;

import com.sliit.spm.model.Line;
import com.sliit.spm.model.Project;
import com.sliit.spm.model.ProjectFile;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author vimukthi_r
 * @Date Jul 23, 2019
 * @Description
 * @Version
 */
public class FileHandler {

    private static final Logger LOGGER = Logger.getLogger(FileHandler.class);

    private Project project;
    private String projectRoot = "";
    private List<File> fileList = new ArrayList<>();
    private List<ProjectFile> projectFiles = new ArrayList<>();

    public void readFiles(Project p) {
        this.project = p;
        this.projectRoot = project.getSourcePath();
        getFiles(projectRoot);
        calculateComplexity();
        this.project.setFiles(projectFiles);
    }

    public void getFiles(String projectPath) {
        File dir = new File(projectPath);
        File[] directoryListing = dir.listFiles();
        if (Objects.nonNull(directoryListing)) {
            for (File file : directoryListing) {
                if (file.isDirectory()) {
                    getFiles(file.getPath());
                }
                if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("java")
                        || FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("cpp")) {
                    fileList.add(file);
                }
            }
        }
    }

    private void calculateComplexity() {
        LOGGER.info("Found " + fileList.size() + " Files in source path");
        for (File file : fileList) {
            ProjectFile projectFile = new ProjectFile();

            try (LineNumberReader lnr = new LineNumberReader(new FileReader(file))) {

                LOGGER.debug("Analyzing file " + file.getCanonicalPath().replace(projectRoot, ""));
                projectFile.setRelativePath(file.getCanonicalPath().replace(projectRoot, ""));

                List<Line> lines = new ArrayList<>();

                for (String line; (line = lnr.readLine()) != null; ) {
                    Line lineObj = new Line();
                    lineObj.setLineNo(lnr.getLineNumber());
                    lineObj.setData(line);


                    Cs.calcCs(lineObj, line);

                    lines.add(lineObj);
                }

                projectFile.setLinesData(lines);
                System.out.println(projectFile);
//                 TODO calc and set Cp
//                projectFile.setCp();

                projectFiles.add(projectFile);

            } catch (IOException e) {
                LOGGER.error("Error reading file", e);
            }
        }
    }
}