package PreProcess;

import java.util.*;
import java.io.*;

import org.xml.sax.*;

public class Data {
	HashMap<Integer, String> id2Text;
	HashMap<String, Integer> text2Id;
	List<Integer> idList;

	public Data() {
		id2Text = new HashMap<Integer, String>();
		text2Id = new HashMap<String, Integer>();
		idList = new ArrayList<Integer>();
	}

	public HashMap<Integer, String> getId2Text() {
		return id2Text;
	}

	public void setId2Text(HashMap<Integer, String> id2Text) {
		this.id2Text = id2Text;
	}

	public HashMap<String, Integer> getText2Id() {
		return text2Id;
	}

	public void setText2Id(HashMap<String, Integer> text2Id) {
		this.text2Id = text2Id;
	}

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	public void loadIn(String filePath) {
		// InputStreamReader reader = new InputStreamReader(new
		// FileInputStream(new File(filePath)), "UTF-8");
		// BufferedReader bufferedReader = new BufferedReader(reader);
		// String line;
		// while ((line = bufferedReader.readLine()) != null) {
		//
		// }
		ArrayList<Map<String, String>> list = (ArrayList<Map<String, String>>) SaxService
				.ReadXML(filePath, "doc");

		// System.out.println(list.toString());
		for (Map<String, String> ele : list) {
			id2Text.put(Integer.parseInt(ele.get("id")), ele.get("review"));
//			if (text2Id.containsKey(ele.get("review"))){
//				System.out.println(ele.get("review")+" "+ele.get("id"));
//			}
			text2Id.put(ele.get("review"), Integer.parseInt(ele.get("id")));
			idList.add(Integer.parseInt(ele.get("id")));
		}
	}

	public Data clone(){
		Data re = new Data();
		re.id2Text = (HashMap<Integer, String>) this.id2Text.clone();
		re.text2Id = (HashMap<String, Integer>) this.text2Id.clone();
		re.idList = new ArrayList<Integer>(this.idList);
		return re;
	}
	
	public Data combine(Data tar){
		Data re = this.clone();
		re.id2Text.putAll(tar.getId2Text());
		re.text2Id.putAll(tar.text2Id);
		for (int id:tar.getIdList()){
			if (!re.idList.contains(id)){
				re.idList.add(id);
			}
		}
//		re.idList.addAll(tar.getIdList());
		if (re.id2Text.size() != re.idList.size()){
			System.out.println("error");
		}
		return re;
	}
	
	public ArrayList<Data> sample(double percent){
		ArrayList<Data> re = new ArrayList<Data>();
		Random random = new Random(0);
		re.add(new Data());
		re.add(new Data());
		for (int id:this.idList){
			Data dataTmp;
			if (random.nextFloat()<=percent){
				dataTmp = re.get(0);
			}
			else{
				dataTmp = re.get(1);
			}
			dataTmp.getId2Text().put(id, this.id2Text.get(id));
			dataTmp.getText2Id().put(this.id2Text.get(id), id);
			dataTmp.getIdList().add(id);
		}
		return re;
	}
	
	public ArrayList<Data> sampleSeq(double percent){
		ArrayList<Data> re = new ArrayList<Data>();
		re.add(new Data());
		re.add(new Data());
		int count = 0;
		for (int id:this.idList){
			Data dataTmp;
			if (count<=percent*this.idList.size()){
				dataTmp = re.get(0);
			}
			else{
				dataTmp = re.get(1);
			}
			dataTmp.getId2Text().put(id, this.id2Text.get(id));
			dataTmp.getText2Id().put(this.id2Text.get(id), id);
			dataTmp.getIdList().add(id);
			count++;
		}
		return re;
	}
}
