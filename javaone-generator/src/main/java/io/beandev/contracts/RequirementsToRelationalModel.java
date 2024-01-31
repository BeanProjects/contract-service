package io.beandev.contracts;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;
import java.util.Properties;
import static java.lang.System.out;

public class RequirementsToRelationalModel {

    public static void main(String[] args) {
        out.println("Hello, World!");

        // Example requirements document
        String requirementsDocument = "The system should allow users to create and manage tasks. " +
                "Each task has a unique identifier, a description, and a due date. " +
                "Users can assign tasks to other users. " +
                "Joe Smith was born in California";

        // Set up Stanford CoreNLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
        props.setProperty("ner.debug", "true"); // Enable debug mode

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Perform NLP processing to extract entities
        Annotation document = new Annotation(requirementsDocument);
        pipeline.annotate(document);

        // Extract entities
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        if (sentences.isEmpty()) {
            System.out.println("No sentences found in the document");
            return;
        }
        out.println(sentences.size() + " sentences found in the document.");
        for (CoreMap sentence : sentences) {
            out.println("Processing sentence: " + sentence);
            for (CoreMap token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String nerTag = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
//                if (!nerTag.equals("O")) {
                    String word = token.get(CoreAnnotations.TextAnnotation.class);
                    System.out.println("Entity Type: " + nerTag + ", Entity Text: " + word);
//                }
            }
        }

        // Close the StanfordCoreNLP pipeline
//        pipeline.close(); // Removed from library
    }
}
