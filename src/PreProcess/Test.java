package PreProcess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Classify.*;

public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String path1 = "E:\\Jinyi\\Project\\SA\\Data\\evaltask2_sample_data\\en_sample_data\\";
		String path2 = "E:/Jinyi/Project/SA/";
//		String path1 = "/home/jinyi/Project/SA/";
//		String path2 = "/home/jinyi/Project/SA/";
		String positiveData = path1+"sample.positive.txt";
		String negativeData = path1+"sample.negative.txt";
		Data dataPo = new Data();
		dataPo.loadIn(positiveData);
		Data dataNe = new Data();
		dataNe.loadIn(negativeData);
//		System.out.println(dataPo.getIdList().size()+" "+dataNe.getIdList().size());
//		System.out.println(Collections.max(dataPo.getIdList()));
//		System.out.println(Collections.min(dataPo.getIdList()));
//		System.out.println(Collections.max(dataNe.getIdList()));
//		System.out.println(Collections.min(dataNe.getIdList()));
		////
		Data data = dataPo.combine(dataNe);
//		System.out.println(dataPo.getId2Text().size()+" "+dataNe.getId2Text().size()+" "+data.getId2Text().size());
		ArrayList<Data> dataSample = data.sample(0.9);
//		Unigram ug = new Unigram();
//		ug.calFrequency(dataSample.get(1), path+"textFreqA75.txt", "wholeFreqA75.txt");
		
//		UnigramFeature uf1 = new UnigramFeature();
//		uf1.loadIn(path+"t1.txt");
//		UnigramFeature uf2 = new UnigramFeature();
//		uf2.loadIn(path+"t2.txt");
//		System.out.println(uf1.combine(uf2).getTextFreq());
//		System.out.println(uf1.combine(uf2).getWholeFreq());
		
		UnigramFeature uf1 = new UnigramFeature();
		UnigramFeature uf2 = new UnigramFeature();
		UnigramFeature uf3 = new UnigramFeature();
		UnigramFeature uf = new UnigramFeature();
		uf1.loadIn(path2+"textFreq.txt");
		uf2.loadIn(path2+"textFreqA5.txt");
		uf3.loadIn(path2+"textFreqA75.txt");
		uf = uf.combine(uf1);
		uf = uf.combine(uf2);
		uf = uf.combine(uf3);
		UnigramFeature ufTrain = uf.sample(dataSample.get(0).getIdList());
		UnigramFeature ufTest = uf.sample(dataSample.get(1).getIdList());
//		ArrayList tmp = new ArrayList<Integer>();
//		tmp.add(1);
//		tmp.add(3);
//		uf = uf.sample(tmp);
//		System.out.println(uf1.getTextFreq().size()+" "+uf2.getTextFreq().size()+" "+uf3.getTextFreq().size());
//		System.out.println(uf.getTextFreq().size());
//		System.out.println(uf.getWholeFreq().size());
		ufTrain.genFeatureWords(200);
		ufTest.setFeatureWords(ufTrain.getFeatureWords());
		System.out.println(ufTrain.getFeatureWords());
//		System.out.println(uf.getFeatureVector(1));
		Classification ca = new Classification();
		ca.genArff(path2+"b90a200.arff", ufTrain);
		ca.genArff(path2+"b10a200.arff", ufTest);
//		ca.classify(path2+"b90a100.arff", ufTest);
	}

}