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
		String negativeData = "/Users/jinyi/Documents/College/Course/IE/Data/evaltask2_sample_data/en_sample_data/sample.positive.txt";
		Data data = new Data();
		data.loadIn(negativeData);

		System.out.println(data.getId2Text().size());
		System.out.println(data.getIdList().size());

		System.out.println(data.getId2Text().get(0));

		// Tokenize tokenize = new Tokenize(data.getId2Text().get(0));
		// System.out.println(tokenize.tokenize().toString());
		String str = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		while (str != null) {
			DependencyParse dp = new DependencyParse(str);
			System.out.println(dp.negParse());
			str = br.readLine();
//			Negation neg = new Negation(str);
//			neg.changeNegation();
//			str = br.readLine();
		}

	}

}