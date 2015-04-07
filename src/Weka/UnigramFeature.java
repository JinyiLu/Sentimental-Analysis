package Weka;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class UnigramFeature {
	private HashMap<Integer, HashMap<String, Integer>> textFreq;
	private HashMap<String, Integer> wholeFreq;
	private ArrayList<String> featureWords;
	
	public UnigramFeature(){
		textFreq = new HashMap<Integer, HashMap<String, Integer>>();
		featureWords = new ArrayList<String>();
		wholeFreq = new HashMap<String, Integer>();
	}
	
	public void loadIn(String filePath) throws Exception{
		InputStreamReader reader = new InputStreamReader(new FileInputStream(new File(filePath)), "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line;
		
		while ((line = bufferedReader.readLine()) != null) {
			String[] lineSp = line.split("\t");
			if (textFreq.containsKey(Integer.parseInt(lineSp[0]))){
				System.out.println("error3");
			}
			else{
				HashMap<String, Integer> wFreq = new HashMap<String, Integer>();
				for (int i=1; i<lineSp.length; ++i){
					if (i%2==1){
						if (i+1>=lineSp.length){
							System.out.println("error4");
						}
						else{
							wFreq.put(lineSp[i], Integer.parseInt(lineSp[i+1]));
							if (wholeFreq.containsKey(lineSp[i])){
								wholeFreq.put(lineSp[i], wholeFreq.get(lineSp[i])+Integer.parseInt(lineSp[i+1]));
							}
							else{
								wholeFreq.put(lineSp[i], Integer.parseInt(lineSp[i+1]));
							}
						}
					}
				}
				textFreq.put(Integer.parseInt(lineSp[0]), wFreq);
			}
		}
	}
	
	public HashMap<Integer, HashMap<String, Integer>> getTextFreq() {
		return textFreq;
	}

	public void setTextFreq(HashMap<Integer, HashMap<String, Integer>> textFreq) {
		this.textFreq = textFreq;
	}

	public HashMap<String, Integer> getWholeFreq() {
		return wholeFreq;
	}

	public void setWholeFreq(HashMap<String, Integer> wholeFreq) {
		this.wholeFreq = wholeFreq;
	}

	public ArrayList<String> getFeatureWords() {
		return featureWords;
	}

	public void setFeatureWords(ArrayList<String> featureWords) {
		this.featureWords = featureWords;
	}

	public UnigramFeature clone(){
		UnigramFeature re = new UnigramFeature();
		re.setTextFreq((HashMap<Integer, HashMap<String, Integer>>) this.textFreq.clone());
		re.setWholeFreq((HashMap<String, Integer>) this.wholeFreq.clone());
		return re;
	}
	
	public UnigramFeature combine(UnigramFeature tar){
		UnigramFeature re = this.clone();
		re.getTextFreq().putAll(tar.getTextFreq());
		for (String word:tar.getWholeFreq().keySet()){
			if (re.getWholeFreq().containsKey(word)){
				re.getWholeFreq().put(word, re.getWholeFreq().get(word)+tar.getWholeFreq().get(word));
			}
			else{
				re.getWholeFreq().put(word, tar.getWholeFreq().get(word));
			}
		}
		return re;
	}
	
	public void genFeatureWords(int threshold){
		for (String word:this.wholeFreq.keySet()){
			int freq = this.wholeFreq.get(word);
			if (freq>=threshold){
				this.featureWords.add(word);
			}
		}
	}
	
	public ArrayList<Integer> getFeatureVector(int id){
		if (this.textFreq.containsKey(id)){
			ArrayList<Integer> fv = new ArrayList<Integer>();
			HashMap<String, Integer> tf = this.textFreq.get(id);
			for (String word:this.featureWords){
				if (tf.containsKey(word)){
					fv.add(tf.get(word));
				}
				else{
					fv.add(0);
				}
			}
			return fv;
		}
		else{
			return null;
		}
	}
	
	public void updateWholeFreq(){
		wholeFreq.clear();
		for (int id:textFreq.keySet()){
			HashMap<String, Integer> twFreq = textFreq.get(id);
			for (String word:twFreq.keySet()){
				if (wholeFreq.containsKey(word)){
					wholeFreq.put(word, wholeFreq.get(word)+twFreq.get(word));
				}
				else{
					wholeFreq.put(word, twFreq.get(word));
				}
			}
		}
	}
	
	public UnigramFeature sample(List<Integer> IDs){
		UnigramFeature uf = this.clone();
		for (int id:this.getTextFreq().keySet()){
			if (!IDs.contains(id)){
				uf.getTextFreq().remove(id);
			}
		}
		uf.updateWholeFreq();
		return uf;
	}

}
