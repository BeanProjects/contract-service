package io.beandev.contracts;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.trees.EnglishGrammaticalRelations;
import edu.stanford.nlp.trees.UniversalEnglishGrammaticalRelations;
import edu.stanford.nlp.util.CoreMap;

import java.util.Properties;

import static java.lang.System.out;

/**
 * Quickstart (with convenience wrappers)
 * Below is a quick snippet of code that demonstrates running a full pipeline on some sample text. It requires the english and english-kbp models jars which contain essential resources.
 * It uses new wrapper classes that have been developed for Stanford CoreNLP 3.9.0 to make it easier to work with annotations.
 * Please note that this new API has not been tested as much as the classic API. Below this section is the documentation for the classic pipeline API.
 */
public class BasicPipelineExample {

    public static String text = "Joe Smith was born in California. " +
            "In 2017, he went to Paris, France in the summer. " +
            "His flight left at 3:00pm on July 10th, 2017. " +
            "After eating some escargot for the first time, Joe said, \"That was delicious!\" " +
            "He sent a postcard to his sister Jane Smith. " +
            "After hearing about Joe's trip, Jane decided she might go to France one day.";

    public static void main(String[] args) {
        // set up pipeline properties
        Properties props = new Properties();
        // set the list of annotators to run
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,dcoref,depparse,coref,kbp,quote");
        // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
        props.setProperty("coref.algorithm", "neural");
        // build pipeline
        out.println("creating pipeline...");
//        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);


//        Properties props = new Properties();
//        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,parse");
//        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
//
//        Annotation doc = new Annotation("I love you.");
//        pipeline.annotate(doc);
//
//        List<CoreMap> sentences = doc.get(CoreAnnotations.SentencesAnnotation.class);
//
//        for (CoreMap sentence : sentences) {
//            SemanticGraph graph = sentence.get(SemanticGraphCoreAnnotations.EnhancedDependenciesAnnotation.class);
//
//            String verb = graph.getFirstRoot().originalText(); // Get the root verb
//
//            String subject = "";
//            String object = "";
//
//            // iterate over all edges in graph
//            for (SemanticGraphEdge edge : graph.edgeIterable()) {
//                if (edge.getRelation().toString().equals("nsubj") && edge.getGovernor().originalText().equals(verb)) {
//                    subject = edge.getDependent().originalText();
//                }
//
//                if ((edge.getRelation().toString().equals("dobj") || edge.getRelation().toString().equals("nmod")) && edge.getGovernor().originalText().equals(verb)) {
//                    object = edge.getDependent().originalText();
//                }
//            }
//
//            System.out.println("Subject: " + subject);
//            System.out.println("Verb: " + verb);
//            System.out.println("Object: " + object);
//        }
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation document = new Annotation("I love you");
        pipeline.annotate(document);
        CoreMap sentence = document.get(CoreAnnotations.SentencesAnnotation.class).getFirst();
        SemanticGraph dependencies = sentence.get(SemanticGraphCoreAnnotations.EnhancedDependenciesAnnotation.class);

        String subject = "";
        String verb = "";
        String object = "";

        // Find verb
        for (IndexedWord iw : dependencies.vertexSet()) {
            if (iw.tag().startsWith("VB")) {
                verb = iw.word();
                break;
            }
        }

        // Find subject and object
        for (SemanticGraphEdge edge : dependencies.edgeIterable()) {
            out.println("Relation for " + edge.toString() + " is " + edge.getRelation());
            out.println("Governor is " + edge.getGovernor());
            out.println("Relation is Direct Object ? " + edge.getRelation().equals(UniversalEnglishGrammaticalRelations.DIRECT_OBJECT));

            var rel = edge.getRelation();
            out.println(rel.getLanguage());
            out.println(rel.getShortName());
            out.println(rel.getLongName());


            // Looking for subject
            if ((edge.getRelation().equals(UniversalEnglishGrammaticalRelations.SUBJECT) || edge.getRelation().equals(UniversalEnglishGrammaticalRelations.NOMINAL_SUBJECT)) && edge.getGovernor().word().equals(verb)) {
                subject = edge.getDependent().word();
            }

            // Looking for direct and indirect objects
            if ((edge.getRelation().equals(EnglishGrammaticalRelations.OBJECT) || edge.getRelation().equals(UniversalEnglishGrammaticalRelations.DIRECT_OBJECT) || edge.getRelation().equals(UniversalEnglishGrammaticalRelations.INDIRECT_OBJECT)) && edge.getGovernor().word().equals(verb)) {
                object = edge.getDependent().word();
            }
        }

        System.out.println("Subject: " + subject);
        System.out.println("Verb: " + verb);
        System.out.println("Object: " + object);



//        // create an empty Annotation just with the given text
//        Annotation document2 = new Annotation("I love you.");
//        // run all Annotators on this text
//        pipeline.annotate(document2);
//
//        // get the dependency tree
//        CoreMap sentence2 = document2.get(CoreAnnotations.SentencesAnnotation.class).getFirst();
//        SemanticGraph dependencies = sentence2.get(SemanticGraphCoreAnnotations.EnhancedPlusPlusDependenciesAnnotation.class);
//
//        // extract subject, verb and object
//        IndexedWord root = dependencies.getFirstRoot();
//        String verb = root.word();
//        IndexedWord subject = dependencies.getChildWithReln(root, EnglishGrammaticalRelations.SUBJECT);
//        IndexedWord object = dependencies.getChildWithReln(root, EnglishGrammaticalRelations.OBJECT);
//
//        dependencies.prettyPrint();
//
//        System.out.println("Subject: " + subject);
//        System.out.println("Verb: " + verb);
//        System.out.println("Object: " + object);

//
//        out.println("creating document object...");
//        // create a document object
//        CoreDocument document = new CoreDocument(text);
//
//        out.println("annotate the document...");
//        // annnotate the document
////        pipeline.annotate(document);
        // examples

//        out.println("get 10th token...");
//        // 10th token of the document
//        CoreLabel token = document.tokens().get(10);
//        System.out.println("Example: token");
//        System.out.println(token);
//        System.out.println();
//
//        // text of the first sentence
//        String sentenceText = document.sentences().get(0).text();
//        System.out.println("Example: sentence");
//        System.out.println(sentenceText);
//        System.out.println();
//
        // second sentence
//        CoreSentence sentence = document.sentences().get(1);
//
//        out.println("Second sentence is " + sentence);
//        // list of the part-of-speech tags for the second sentence
//        List<String> posTags = sentence.posTags();
//        System.out.println("Example: pos tags");
//        System.out.println(posTags);
//        System.out.println();
//
//        // list of the ner tags for the second sentence
//        List<String> nerTags = sentence.nerTags();
//        System.out.println("Example: ner tags");
//        System.out.println(nerTags);
//        System.out.println();
//
//        // constituency parse for the second sentence
//        Tree constituencyParse = sentence.constituencyParse();
//        System.out.println("Example: constituency parse");
//        System.out.println(constituencyParse);
//        System.out.println();
//
        // dependency parse for the second sentence
//        SemanticGraph dependencyParse = sentence.dependencyParse();
////        System.out.println("Example: dependency parse");
////        System.out.println(dependencyParse);
//        dependencyParse.prettyPrint();
//        out.println("Roots are");
//        dependencyParse.getRoots().forEach(out::println);
//        dependencyParse.getRoots().stream().findFirst().ifPresent(root -> {
//            out.println("Root is " + root);
//            out.println("Children are");
//            dependencyParse.getChildren(root).forEach(out::println);
//        });
//        System.out.println();
//
//        // kbp relations found in fifth sentence
//        List<RelationTriple> relations =
//                document.sentences().get(4).relations();
//        System.out.println("Example: relation");
//        System.out.println(relations.get(0));
//        System.out.println();
//
//        // entity mentions in the second sentence
//        List<CoreEntityMention> entityMentions = sentence.entityMentions();
//        System.out.println("Example: entity mentions");
//        System.out.println(entityMentions);
//        System.out.println();
//
//        // coreference between entity mentions
//        CoreEntityMention originalEntityMention = document.sentences().get(3).entityMentions().get(1);
//        System.out.println("Example: original entity mention");
//        System.out.println(originalEntityMention);
//        System.out.println("Example: canonical entity mention");
//        System.out.println(originalEntityMention.canonicalEntityMention().get());
//        System.out.println();
//
//        // get document wide coref info
//        Map<Integer, CorefChain> corefChains = document.corefChains();
//        System.out.println("Example: coref chains for document");
//        System.out.println(corefChains);
//        System.out.println();
//
//        // get quotes in document
//        List<CoreQuote> quotes = document.quotes();
//        CoreQuote quote = quotes.get(0);
//        System.out.println("Example: quote");
//        System.out.println(quote);
//        System.out.println();
//
//        // original speaker of quote
//        // note that quote.speaker() returns an Optional
//        System.out.println("Example: original speaker of quote");
//        System.out.println(quote.speaker().get());
//        System.out.println();
//
//        // canonical speaker of quote
//        System.out.println("Example: canonical speaker of quote");
//        System.out.println(quote.canonicalSpeaker().get());
//        System.out.println();
    }
}
