package Classify;

import java.util.*;

import PreProcess.Data;

public class Unigram {
	private HashMap<String, Integer> frequency;
	
	public Unigram(){
		frequency = new HashMap<String, Integer>();
	}
	
	public void calFrequency(Data data){
		for (int id:data.getIdList()){
			DependencyParse dp = new DependencyParse(data.getId2Text().get(id));
			List<String> words = dp.negParse();
			for (String word:words){
				if (frequency.containsKey(word)){
					frequency.put(word, frequency.get(word)+1);
				}
				else{
					frequency.put(word, 1);
				}
			}
		}
		System.out.println(frequency.size());
	}
	
	
}
