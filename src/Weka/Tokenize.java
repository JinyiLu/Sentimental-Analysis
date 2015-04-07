package Weka;

import java.io.StringReader;
import java.util.*;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;

public class Tokenize {
	private String text;

	public Tokenize(String text) {
		super();
		this.text = text;
	}

	public ArrayList<CoreLabel> tokenize() {

		PTBTokenizer ptbt = new PTBTokenizer(new StringReader(text),
				new CoreLabelTokenFactory(), "");
		ArrayList<CoreLabel> tokenSet = new ArrayList<CoreLabel>();
		for (CoreLabel label; ptbt.hasNext();) {
			label = (CoreLabel) ptbt.next();
//			System.out.println(label.toString()+" "+label);
			tokenSet.add(label);
		}
		return tokenSet;
	}

}
