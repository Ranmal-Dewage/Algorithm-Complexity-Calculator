package com.sliit.spm.utils;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodAndVariableFinder {

    private static Pattern method = Pattern.compile("(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])");

    public static List<String> getMethodandVariables(LineNumberReader lnr) throws IOException {
        List<String> list = new ArrayList<>();
        for (String line; (line = lnr.readLine()) != null; ) {
            Matcher m = method.matcher(line);
            while (m.find()) {
                String name = m.group(2);
                if(!list.contains(name)){
                    list.add(name);
                }
            }
        }
        System.out.println(list);
        return list;
    }

}
