package PreProcess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Classify.*;

public class Test {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String positiveData = "/Users/jinyi/Documents/College/Course/IE/Data/evaltask2_sample_data/en_sample_data/sample.positive.txt";
		String negativeData = "/Users/jinyi/Documents/College/Course/IE/Data/evaltask2_sample_data/en_sample_data/sample.negative.txt";
		Data dataPo = new Data();
		dataPo.loadIn(positiveData);
		Data dataNe = new Data();
		dataNe.loadIn(negativeData);

		Data data = dataPo.combine(dataNe);
		
		System.out.println(dataPo.getId2Text().size()+" "+dataPo.getIdList().size()+" "+dataPo.getText2Id().size());
		System.out.println(dataNe.getId2Text().size()+" "+dataNe.getIdList().size()+" "+dataNe.getText2Id().size());
		System.out.println(data.getId2Text().size()+" "+data.getIdList().size()+" "+data.getText2Id().size());	
		
		System.out.println(data.getId2Text().get(112));
		// Tokenize tokenize = new Tokenize(data.getId2Text().get(0));
		// System.out.println(tokenize.tokenize().toString());

	}

}