package com.sliit.spm.acc;

import com.sliit.spm.model.Line;
import com.sliit.spm.model.Project;
import com.sliit.spm.model.ProjectFile;
import com.sliit.spm.utils.Client;
import com.sliit.spm.utils.MethodAndVariableFinder;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        Client.sendAnalysisData(project);

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
        fileList.forEach(file -> {
            ProjectFile projectFile = new ProjectFile();

            try (LineNumberReader lnr = new LineNumberReader(new FileReader(file))) {

                LOGGER.debug("Analyzing file " + file.getCanonicalPath().replace(projectRoot, ""));
                projectFile.setRelativePath(file.getCanonicalPath().replace(projectRoot, ""));
                List<Line> lines = new ArrayList<>();
                boolean singleLineCommented = false;
                boolean multiLineCommented = false;

                // helper for Cs calculation
                List<String> methodsAndVariables = MethodAndVariableFinder.getMethodAndVariables(file);

                for (String line; (line = lnr.readLine()) != null; ) {
                    Line lineObj = new Line();
                    lineObj.setLineNo(lnr.getLineNumber());
                    lineObj.setData(line);

                    // ignore comment lines
                    if (line.trim().startsWith("//") || line.trim().startsWith("import") || line.trim().startsWith("include")) {
                        singleLineCommented = true;
                    } else {
                        singleLineCommented = false;
                    }
                    if (line.trim().startsWith("/*")) {
                        multiLineCommented = true;
                    }
                    if (line.trim().startsWith("*/")) {
                        line = line.replaceFirst("\\*/", "");
                        multiLineCommented = false;
                    }
                    if (line.contains("//")) {
                        line = line.replace(line.substring(line.indexOf("//")), "");
                    }

                    //calculate complexity if line is not commented
                    if (!singleLineCommented && !multiLineCommented) {
                        Cs.calcCs(lineObj, line, methodsAndVariables);
                        Ci.calcCi(lineObj, line);
                    }

                    if (line.trim().endsWith("*/")) {
                        multiLineCommented = false;
                    }

                    lines.add(lineObj);
                }

                projectFile.setLinesData(lines);
//                 TODO calc and set Cp
//                projectFile.setCp();

                projectFiles.add(projectFile);
                Ci.resetCi();

            } catch (IOException e) {
                LOGGER.error("Error reading file", e);
            }
        });
    }
}