/**
 * 
 */
package com.sliit.spm.acc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.io.FilenameUtils;

import com.sliit.spm.model.Project;

/**
 * @author vimukthi_r
 * @Date Jul 23, 2019
 * @Description
 * @Version
 */
public class FileHandler {

	private String projectRoot = "";
	
	public void readFiles(Project project) {
		this.projectRoot = project.getSourcePath();
		getFiles(projectRoot);
	}
	
	public void getFiles(String projectPath) {
		File dir = new File(projectPath);
		File[] directoryListing = dir.listFiles();
		if (Objects.nonNull(directoryListing)) {
			for (File file : directoryListing) {
				if(file.isDirectory()) {
					getFiles(file.getPath());
				}
				if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("java")
						|| FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("cpp")) {
					try (BufferedReader reader = new BufferedReader(new FileReader(file));) {
						//String currentLine = reader.readLine();
						System.out.println(file.getPath().replace(this.projectRoot, ""));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
