package Classify;

import java.io.*;
import java.util.*;

import edu.stanford.nlp.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.*;

public class DependencyParse {
	private String str;
	
	public DependencyParse(String str){
		this.str = str;
	}
	
	public void depencyParse() {
		PrintWriter out = new PrintWriter(System.out);
		
		StanfordCoreNLP pipeline = new StanfordCoreNLP();
		Annotation annotation = new Annotation(this.str);

		pipeline.annotate(annotation);
		pipeline.prettyPrint(annotation, out);

		// An Annotation is a Map and you can get and use the various analyses
		// individually.
		// For instance, this gets the parse tree of the first sentence in the
		// text.
		out.println();
		// The toString() method on an Annotation just prints the text of the
		// Annotation
		// But you can see what is in it with other methods like
		// toShorterString()
		out.println("The top level annotation");
		out.println(annotation.toShorterString());
		List<CoreMap> sentences = annotation
				.get(CoreAnnotations.SentencesAnnotation.class);
		if (sentences != null && sentences.size() > 0) {
			ArrayCoreMap sentence = (ArrayCoreMap) sentences.get(0);
			out.println("The first sentence is:");
			out.println(sentence.toShorterString());
			Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
			out.println();
			out.println("The first sentence tokens are:");
			for (CoreMap token : sentence
					.get(CoreAnnotations.TokensAnnotation.class)) {
				ArrayCoreMap aToken = (ArrayCoreMap) token;
				out.println(aToken.toShorterString());
			}
			out.println("The first sentence parse tree is:");
			tree.pennPrint(out);
			out.println("The first sentence basic dependencies are:");
			System.out
					.println(sentence
							.get(SemanticGraphCoreAnnotations.BasicDependenciesAnnotation.class)
							.toString(SemanticGraph.OutputFormat.LIST));
			out.println("The first sentence collapsed, CC-processed dependencies are:");
			SemanticGraph graph = sentence
					.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
			System.out.println(graph.toString(SemanticGraph.OutputFormat.LIST));
		}
	}

}
