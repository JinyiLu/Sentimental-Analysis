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
			text2Id.put(ele.get("review"), Integer.parseInt(ele.get("id")));
			idList.add(Integer.parseInt(ele.get("id")));
		}
	}

}
