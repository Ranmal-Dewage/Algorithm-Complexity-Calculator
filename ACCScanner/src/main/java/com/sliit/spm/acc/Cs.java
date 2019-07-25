package com.sliit.spm.acc;

import com.sliit.spm.model.Line;
import com.sliit.spm.utils.PropertyReader;

import java.util.Arrays;
import java.util.List;

public class Cs {
    public static void calcCs(Line lineObj, String line) {

        int cs = 0;
        List<String> keywords = Arrays.asList(PropertyReader.getInstance().getProperty("cs").split(","));

        for (String keyword : keywords) {
            if (!line.isEmpty() && line.toLowerCase().indexOf(keyword.toLowerCase()) != -1) {
                cs++;
            }
        }
        lineObj.setCs(cs);
    }
}
