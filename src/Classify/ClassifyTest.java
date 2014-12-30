package Classify;

import java.util.*;
import java.io.*;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import PreProcess.Data;

public class ClassifyTest {
	private ArrayList<String> features;
//	private Data testData;
	private Classifier classifier;
	private Instances format;
	
	public ClassifyTest(){
//		testData = new Data();
		features = new ArrayList<String>();
	}
	
	public void loadIn(String featurePath, String modelPath, String trainPath) throws Exception{
//		testData.loadIn(testPath);
		InputStreamReader reader = new InputStreamReader(new FileInputStream(new File(featurePath)), "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			features.add(line);
		}
		System.out.println("num of features "+features.size());
		classifier = (Classifier)weka.core.SerializationHelper.read(modelPath);
//		Instances trainInstances = new Instances(new BufferedReader(new FileReader(trainPath)));
		format = new Instances(new BufferedReader(new FileReader(trainPath)));
		format.setClassIndex(format.numAttributes() - 1);
	}
	
	public double classify(String sentence) throws Exception{
		Unigram ug = new Unigram();
		HashMap<String, Integer> tokens = ug.calSenFrequency(sentence);
		
		Instance testInstance = new Instance(format.numAttributes());
		int i = 0;
		for (String word:features){
			if (tokens.containsKey(word)){
				testInstance.setValue(format.attribute(i++), tokens.get(word));
			}
			else{
				testInstance.setValue(format.attribute(i++), 0);
			}
		}
		
		testInstance.setDataset(format);
		double score = classifier.classifyInstance(testInstance);
		
		return score;
	}
	
}
