package com.sliit.spm.model;

import java.util.ArrayList;
import java.util.List;

public class ProjectFile {

    List<ProjectFile> files = new ArrayList();


    public List<ProjectFile> getFiles() {
        return files;
    }

    public void setFiles(List<ProjectFile> files) {
        this.files = files;
    }

}
