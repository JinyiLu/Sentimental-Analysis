package PreProcess;

/**
 * 程序入口
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	String negativeData = "/Users/jinyi/Documents/College/Course/IE/Data/evaltask2_sample_data/en_sample_data/sample.positive.txt";
    	Data data = new Data();
    	data.loadIn(negativeData);

    	System.out.println(data.getId2Text().size());
    }

}