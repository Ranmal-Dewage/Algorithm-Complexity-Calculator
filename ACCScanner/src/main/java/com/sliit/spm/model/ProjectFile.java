package com.sliit.spm.model;

import java.util.List;

public class ProjectFile {

    String relativePath;
    List<Line> linesData;
    int cp;

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public List<Line> getLinesData() {
        return linesData;
    }

    public void setLinesData(List<Line> linesData) {
        this.linesData = linesData;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

}
