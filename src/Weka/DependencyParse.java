package Weka;

import java.io.*;
import java.util.*;

import edu.stanford.nlp.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.*;

public class DependencyParse {
	private String str;
	private static StanfordCoreNLP pipeline = new StanfordCoreNLP();

	public DependencyParse(String str) {
		this.str = str;
	}

	public List<String> negParse() {
		PrintWriter out = new PrintWriter(System.out);

//		StanfordCoreNLP pipeline = new StanfordCoreNLP();
		Annotation annotation = new Annotation(this.str);

		pipeline.annotate(annotation);
//		pipeline.prettyPrint(annotation, out);

		// out.println();
		// out.println("The top level annotation");
		// out.println(annotation.toShorterString());
		List<CoreMap> sentences = annotation
				.get(CoreAnnotations.SentencesAnnotation.class);
		
		
//		List<CoreLabel> results = new ArrayList<CoreLabel>();
		List<String> results = new ArrayList<String>();
		
		if (sentences !=null){
			for (CoreMap sentence:sentences){
				Set<Integer> negGovs = new HashSet<Integer>();
//				List<CoreLabel> tokens = new ArrayList<CoreLabel>();
				
				SemanticGraph graph = sentence
						.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
//				System.out.println("##");
				for (SemanticGraphEdge edge : graph.edgeIterable()) {
					if (edge.getRelation().toString().equals("neg")) {
//						System.out.println(edge.toString());
//						negEdges.add(edge);
						negGovs.add(edge.getGovernor().index());
//						System.out.println(edge.getTarget().toString()+" "+edge.getGovernor().toString());
					}
				}
				for (CoreLabel token : sentence
						.get(CoreAnnotations.TokensAnnotation.class)){
//					tokens.add(token);
					
					if (negGovs.contains(token.index())){
//						System.out.println("**neg**"+token.toString());
						results.add("NOT_"+token.get(TextAnnotation.class));
					}
					else{
//						System.out.println(token.toString());
						results.add(token.get(TextAnnotation.class));
					}
				}
			}
		}
//		System.out.println(results.toString());
		return results;
	}
}
		
//		return negEdges;
		
//		if (sentences != null && sentences.size() > 0) {
//			ArrayCoreMap sentence = (ArrayCoreMap) sentences.get(0);
//			// out.println("The first sentence is:");
//			// out.println(sentence.toShorterString());
////			Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
//			// out.println();
//			// out.println("The first sentence tokens are:");
////			for (CoreMap token : sentence
////					.get(CoreAnnotations.TokensAnnotation.class)) {
////				ArrayCoreMap aToken = (ArrayCoreMap) token;
////				// out.println(aToken.toShorterString());
////			}
//
//			// out.println("The first sentence parse tree is:");
//			// tree.pennPrint(out);
//			// out.println("The first sentence basic dependencies are:");
//			// System.out
//			// .println(sentence
//			// .get(SemanticGraphCoreAnnotations.BasicDependenciesAnnotation.class)
//			// .toString(SemanticGraph.OutputFormat.LIST));
//			// out.println("The first sentence collapsed, CC-processed dependencies are:");
//			SemanticGraph graph = sentence
//					.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
//			// System.out.println(graph.toString(SemanticGraph.OutputFormat.LIST));
//			System.out.println("##");
//			// GrammaticalRelation rel =
//			// EnglishGrammaticalRelations.NEGATION_MODIFIER;
//			// System.out.println(graph.findAllRelns(rel));
//			for (SemanticGraphEdge edge : graph.edgeIterable()) {
//				if (edge.getRelation().toString().equals("neg")) {
//					System.out.println(edge.toString());
//				}
//			}
//			// System.out.println(graph.getChildrenWithReln(graph.getFirstRoot(),
//			// rel));
//			System.out.println();
//			// System.out.println(graph.getChildrenWithReln(graph.getFirstRoot(),
//			// rel));
//			// for (CoreMap token :
//			// sentence.get(CoreAnnotations.TokensAnnotation.class)){
//			// System.out.println(token.tos)
//			// }
//		}

