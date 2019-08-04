package com.sliit.spm.acc;

import com.sliit.spm.model.Line;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;


public class Cr {


    public static void getRecursiveLine(Line lineObj, String line, HashMap<Integer,Integer> recursiveLines) {

        Iterator noOfPairs = recursiveLines.entrySet().iterator();
        while (noOfPairs.hasNext()) {
            Map.Entry pair = (Map.Entry)noOfPairs.next();

            if( 0 > Integer.parseInt(pair.getKey().toString()) && 0 < Integer.parseInt(pair.getValue().toString())) {
                //Get the line object line number an update the CR value
            }
            noOfPairs.remove(); // avoids a ConcurrentModificationException
        }
    }
}
