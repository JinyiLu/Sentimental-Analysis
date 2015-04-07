package Weka;

import java.util.*;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;

public class Negation {
	
	private String str;
	
	public Negation(String str){
		this.str = str;
	}
	
	public void changeNegation(){
//		DependencyParse dp = new DependencyParse(this.str);
//		Set<SemanticGraphEdge> negEdges = dp.negParse();
//		HashSet<IndexedWord> negGov = new HashSet<IndexedWord>();
//		HashSet<Integer> negGovIndex = new HashSet<Integer>();
//		for (SemanticGraphEdge edge:negEdges){
//			negGov.add(edge.getGovernor());
//			negGovIndex.add(edge.getGovernor().index());
//		}
//		Tokenize tokenizer = new Tokenize(this.str);
//		ArrayList<CoreLabel> tokens = tokenizer.tokenize();
//		ArrayList<CoreLabel> resultTokens = new ArrayList<CoreLabel>();
//		for (CoreLabel token:tokens){
////			if (negGov.contains(token)){
//			if (negGovIndex.contains(token.index())){
//				System.out.println(token.toString());
//			}
//		}
	}
	
}
