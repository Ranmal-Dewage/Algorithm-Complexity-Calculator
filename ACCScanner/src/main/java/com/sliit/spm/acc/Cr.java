package com.sliit.spm.acc;

import com.sliit.spm.model.Line;

import java.util.HashMap;
import java.util.Map;

class Cr {


    static void calcCr(Line lineObj, HashMap<Integer, Integer> recursiveLines) {

        //calc cps
        calcCPS(lineObj);

        // Iterates through the hashmap of line numbers
        for (Map.Entry<Integer, Integer> pair : recursiveLines.entrySet()) {

            //Checks if the line number of  the code segment is within the recursive method
            if (lineObj.getLineNo() >= Integer.parseInt(((Map.Entry) pair).getKey().toString()) && lineObj.getLineNo() <= Integer.parseInt(((Map.Entry) pair).getValue().toString())) {
                int lineCRValue = (lineObj.getCps() * 2);
                lineObj.setCr(lineCRValue);
            }
        }
    }

    private static void calcCPS(Line lineObj) {
        int tw = lineObj.getCtc() + lineObj.getCnc() + lineObj.getCi();
        lineObj.setCps(lineObj.getCs() * tw);
    }
}
