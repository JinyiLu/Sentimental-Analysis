package PreProcess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class MakeXML {
	private Data data;
	private Map<Integer, Boolean> score;

	public MakeXML(Data data) {
		this.data = data;
		this.score = new HashMap<Integer, Boolean>();
	}
	
	public void loadInMap(String mapPath) throws Exception{
		InputStreamReader reader = new InputStreamReader(new FileInputStream(new File(mapPath)), "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			if (line.startsWith("id:")){
				int id = Integer.parseInt(line.substring(line.indexOf("id: ")+4, line.indexOf(" score:")));
				double score = Double.parseDouble(line.substring(line.indexOf("score: ")+7));
				System.out.println(""+id+" "+score);
//				this.score.put(id, score);
				if (score>0)
					this.score.put(id, false);
				else
					this.score.put(id, true);
			}
		}
	}

	public void generateXML(String xmlPath) throws Exception{
		Output output = new Output();
		output.loadIn(this.data);
		output.output(this.score, xmlPath);
	}

}
