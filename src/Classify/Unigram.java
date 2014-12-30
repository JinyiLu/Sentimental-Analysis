package Classify;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

import PreProcess.Data;

public class Unigram {
	private HashMap<String, Integer> frequency;
//	private HashMap<Integer, HashMap<String, Integer>> textWordFreq;
	
	public Unigram(){
		frequency = new HashMap<String, Integer>();
//		textWordFreq = new HashMap<Integer, HashMap<String, Integer>>();
	}
	
	public HashMap<String, Integer> calSenFrequency(String sentence) throws Exception{
		HashMap<String, Integer> senFre = new HashMap<String, Integer>();
		
		DependencyParse dp = new DependencyParse(sentence);
		List<String> words = dp.negParse();
		for (String word:words){
			if (senFre.containsKey(word)){
				senFre.put(word, senFre.get(word)+1);
			}
			else{
				senFre.put(word, 1);
			}
		}
		
		return senFre;
	}
	
	public void calFrequency(Data data, String tf, String wf) throws Exception{
		OutputStreamWriter writerTF = new OutputStreamWriter(
				new FileOutputStream(tf), "UTF-8");
		BufferedWriter bufferedWriterTF = new BufferedWriter(writerTF);
		
		OutputStreamWriter writerWF = new OutputStreamWriter(
				new FileOutputStream(wf), "UTF-8");
		BufferedWriter bufferedWriterWF = new BufferedWriter(writerWF);
		
		
		int i=0;
		for (int id:data.getIdList()){
//			System.out.println(id);
			if (i%100==0){
				System.out.println("num "+i);
			}
			i++;
			
//			if (textWordFreq.containsKey(id)){
//				System.out.println("error2");
//			}
//			textWordFreq.put(id, new HashMap<String, Integer>());
//			HashMap<String, Integer> textFreq = textWordFreq.get(id);
			HashMap<String, Integer> textFreq = new HashMap<String, Integer>();
			DependencyParse dp = new DependencyParse(data.getId2Text().get(id));
			List<String> words = dp.negParse();
			for (String word:words){
				if (frequency.containsKey(word)){
					frequency.put(word, frequency.get(word)+1);
				}
				else{
					frequency.put(word, 1);
				}
				if (textFreq.containsKey(word)){
					textFreq.put(word, textFreq.get(word)+1);
				}
				else{
					textFreq.put(word, 1);
				}
			}
//			System.out.println(textFreq);
			
			bufferedWriterTF.write(id+"\t");
			for (String word:textFreq.keySet()){
				bufferedWriterTF.write(word+"\t"+textFreq.get(word)+"\t");
			}
			bufferedWriterTF.newLine();
			bufferedWriterTF.flush();
		}
		for (String word:frequency.keySet()){
			bufferedWriterWF.write(word+"\t"+frequency.get(word)+"\n");
		}
		bufferedWriterWF.flush();
		bufferedWriterWF.close();
//		System.out.println(frequency);
	}
	
	
}
