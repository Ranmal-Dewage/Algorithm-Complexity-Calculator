package com.sliit.spm.utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodAndVariableFinder {

    private static final Logger LOGGER = Logger.getLogger(MethodAndVariableFinder.class);

    private static List<String> keywordsOne = Arrays.asList(PropertyReader.getInstance().getProperty("cs.one").split(","));
    private static Pattern method = Pattern.compile("(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])");
    private static Pattern variable = Pattern.compile("\"[^\"]*\"|((?=_[a-z_0-9]|[a-z])([a-z_0-9]|[a-z0-9\\[\\]])+(?=\\s*=))");
    private static Pattern param = Pattern.compile("\\((.*?)\\)");
    private static Pattern newPattern = Pattern.compile("(new)+\\s\\w+");
    private static Pattern methodCalls = Pattern.compile("([^\\W:.,()\\s]+)\\s*\\(|::([^W:.,()\\s]+)");

    public static List<String> getMethodAndVariables(File file) {
        List<String> list = new ArrayList<>();
        try (LineNumberReader lnr = new LineNumberReader(new FileReader(file))) {
            lnr.lines().parallel().forEach(line -> {

                // method finder
                Matcher m = method.matcher(line);
                while (m.find()) {
                    String name = m.group(2);
                    if (!list.contains(name)) {
                        list.add(name);
                    }
                }

                // method call finder
                Matcher mc = methodCalls.matcher(line);
                while (mc.find()) {
                    String name = mc.group(0).trim().replaceAll("[\\s\\(]", "");
                    if (!list.contains(name)) {
                        list.add(name);
                    }
                }

                // parameter finder example(String a)
                Matcher p = param.matcher(line);
                while (p.find()) {
                    String params = p.group(1);
                    List<String> paramList = Arrays.asList(params.split(","));
                    paramList.forEach(param -> {
                        List<String> single = Arrays.asList(param.trim().split(" "));
                        if (single.size() == 2) {
                            String param1 = single.get(0).replaceAll("[\\[\\]]", "");
                            if (!list.contains(param1)) {
                                list.add(param1);
                            }
                            String param2 = single.get(1).replaceAll("[\\[\\]]", "");
                            if (!list.contains(param2)) {
                                list.add(param2);
                            }
                        }
                    });
                }

                // Object creations finder
                // ex: new FileReader()
                Matcher np = newPattern.matcher(line);
                while (np.find()) {
                    String params = np.group(0);
                    List<String> single = Arrays.asList(params.split(" "));
                    if (single.size() == 2) {
                        String paramName = single.get(1);
                        if (!list.contains(paramName)) {
                            list.add(paramName);
                        }
                    }
                }

                //variable finder
                Matcher v = variable.matcher(line);
                while (v.find()) {
                    String name = v.group(1);
                    if (name != null && !list.contains(name)) {
                        list.add(name.replace("[", "").replace("]", ""));
                    }
                }

                for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
                    String value = iterator.next();
                    if (keywordsOne.contains(value.toLowerCase())) {
                        iterator.remove();
                    }
                }

            });
        } catch (IOException e) {
            LOGGER.error("Error reading file", e);
        }
        return list;
    }

}
