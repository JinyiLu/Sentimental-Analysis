package Classify;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

//import weka.classifiers.functions.LibSVM;
//import weka.core.Instance;
//import weka.core.Instances;
//import weka.core.SelectedTag;
import PreProcess.*;

public class Classification {
	
	public static void genArff(String output, UnigramFeature tData) throws Exception{
		File outputFile = new File(output);
		if (!outputFile.exists()) { 
			outputFile.createNewFile();
		}
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputFile),"UTF-8");
		BufferedWriter bufferedWriter = new BufferedWriter(writer);
		
		
		bufferedWriter.write("@relation review-type\n\n");
		int i=0;
		for (String word:tData.getFeatureWords()){
			i++;
			bufferedWriter.write("@attribute "+i+" numeric\n");
		}
		bufferedWriter.write("@attribute type {1, 2}\n");
		bufferedWriter.write("\n@data\n");
		for (int id:tData.getTextFreq().keySet()){
			ArrayList<Integer> fv = tData.getFeatureVector(id);
			for (int fvv:fv){
//				if (fvv>0)
//					bufferedWriter.write(""+1+",");
//				else 
//					bufferedWriter.write(""+0+",");
				bufferedWriter.write(""+fvv+",");
			}
			if (id<5000){
				bufferedWriter.write("1\n");
			}
			else{
				bufferedWriter.write("2\n");
			}
		}
		
		bufferedWriter.flush();
		bufferedWriter.close();
	}

//	public void classify(String trainPath, UnigramFeature test) throws Exception{
////		Instances trainInstances = new Instances();
//		Instances trainInstances = new Instances(new BufferedReader(new FileReader(trainPath)));
//		Instances format = new Instances(new BufferedReader(new FileReader(trainPath)));
//		trainInstances.setClassIndex(trainInstances.numAttributes() - 1);
//		format.setClassIndex(format.numAttributes() - 1);
//		
////		Classifier classifier =(Classifier)weka.core.SerializationHelper.read(modelPath);
////		Logistic classifier = new Logistic();
//		System.out.println("**loaded**");
//		LibSVM classifier = new LibSVM();
//		weka.core.Tag[] tags = classifier.getKernelType().getTags();
//		classifier.setKernelType(new SelectedTag(0, tags));
//		System.out.println(tags[0]);
//		classifier.buildClassifier(trainInstances);
//		int hit = 0;
//		int total = 0;
//		System.out.println("**start to classify**");
//		for (int id:test.getTextFreq().keySet()){
//			Instance instance = new Instance(format.numAttributes());
//			ArrayList<Integer> fv = test.getFeatureVector(id);
//			for (int i=0; i<fv.size(); ++i){
//				instance.setValue(format.attribute(i),fv.get(i));
//			}
//			instance.setDataset(trainInstances);
//			int cre = (int) classifier.classifyInstance(instance);
//			total++;
//			if (id<5000){
//				if (cre==0)
//					hit++;
//				System.out.println("id: "+id+" re: 0 cre: "+cre);
//			}
//			else{
//				if (cre==1)
//					hit++;
//				System.out.println("id: "+id+" re: 1 cre: "+cre);
//			}
//		}
//		System.out.println(hit+" "+total);
//	}
}
