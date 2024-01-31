package io.beandev.contracts;

import io.cucumber.gherkin.GherkinParser;
import io.cucumber.messages.types.Attachment;
import io.cucumber.messages.types.Envelope;
import io.cucumber.messages.types.GherkinDocument;
import io.cucumber.messages.types.Hook;
import io.cucumber.messages.types.Meta;
import io.cucumber.messages.types.Pickle;
import io.cucumber.messages.types.PickleStep;
import io.cucumber.messages.types.Source;
import io.cucumber.messages.types.Step;
import io.cucumber.messages.types.TestCase;
import io.cucumber.messages.types.TestCaseStarted;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static io.cucumber.messages.types.SourceMediaType.TEXT_X_CUCUMBER_GHERKIN_PLAIN;
import static java.lang.System.out;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        out.println("parent is " + AbstractParent.staticField);
        ChildA.alterParentStaticField(100);
        out.println("parent is " + AbstractParent.staticField);
        ChildA.alterParentStaticField(500);
        out.println("parent is " + AbstractParent.staticField);

        ParentInterface.method();
        ChildB.method();

//        MyClass generatedClass = new MyClass();

        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            out.println("i = " + i);
        }

        final String feature = """
Feature: Is it Friday yet?
  Everybody wants to know when it's Friday
  
  Rule: First Biz Rule
      Scenario: Sunday isn't Friday
        Given today is Sunday
        And it is not Friday
        When I ask whether it's Friday yet
        Then I should be told "Nope"                        
                """;
        final Envelope envelope = Envelope.of(new Source("minimal.feature", feature, TEXT_X_CUCUMBER_GHERKIN_PLAIN));
        GherkinParser parser = GherkinParser.builder().build();
//        Stream<Envelope> pickles = parser.parse(envelope).filter(envl -> envl.getPickle().isPresent());

        // Parse the feature file
        var envelopeStream = parser.parse(envelope);
        // Filter out the AST nodes
        envelopeStream.forEach(
                e -> {
                    out.println(" ------- ");
                    out.println("e = " + e);
                    out.println(" ------- ");
                    Optional<Meta> meta = e.getMeta();
                    meta.ifPresent(value -> out.println("meta = " + value));

                    Optional<Hook> hook = e.getHook();
                    Optional<Source> source = e.getSource();
                    source.ifPresent(value -> out.println("source = " + value));

                    Optional<Attachment> attachment = e.getAttachment();
                    Optional<GherkinDocument> gherkinDocument = e.getGherkinDocument();
                    gherkinDocument.ifPresent(doc ->
                            {
                                out.println("gherkinDocument = " + doc);
                                List<io.cucumber.messages.types.FeatureChild> featureChildren = doc.getFeature().get().getChildren();
                                featureChildren.forEach(
                                        fc -> {
                                            out.println(" ------- FEATURE CHILD ------- ");
                                            out.println("fc = " + fc);
                                            out.println("fc.getBackground() = " + fc.getBackground());
                                            out.println("fc.getRule() = " + fc.getRule().get().getName());
                                            fc.getRule().get().getChildren().forEach(
                                                    r -> {
                                                        out.println("r = " + r);
                                                        out.println("r.getScenario() = " + r.getScenario().get().getName());
                                                        var scenarioOptional = r.getScenario();
                                                        scenarioOptional.ifPresent( scenario -> {
                                                            out.println("scenario = " + scenario.getName());
                                                            List<Step> pickleSteps = scenario.getSteps();
                                                            if(pickleSteps.isEmpty()){
                                                                out.println("No steps found");
                                                            }
                                                            pickleSteps.forEach(
                                                                    ps -> {
                                                                        out.println(" ------- STEPS ------- ");
                                                                        out.println("ps = " + ps);
                                                                        out.println("ps.getId() = " + ps.getId());
                                                                        out.println("ps.getText() = " + ps.getText());
                                                                        out.println("ps.getKeyword() = " + ps.getKeyword());
                                                                        out.println("ps.getKeywordType() = " + ps.getKeywordType());
                                                                        out.println("ps.getDataTable() = " + ps.getDataTable());
                                                                        out.println("ps.getLocation() = " + ps.getLocation());
                                                                        out.println("ps.getDocString() = " + ps.getDocString());
                                                                        out.println(" ------- END STEPS ------- ");

                                                                    }
                                                            );
                                                        });
                                                    }
                                            );
                                            out.println(" ------- END FEATURE CHILD ------- ");
                                        }
                                );
                            }
                    )
                    ;

                    Optional<Source> sourceReference = e.getSource();
                    Optional<TestCase> testResult = e.getTestCase();
                    Optional<TestCaseStarted> testCaseStarted = e.getTestCaseStarted();

                    Optional<Pickle> pickle = e.getPickle();
                    pickle.ifPresent(scenario -> {
                                out.println("scenario = " + scenario.getName());
                                List<PickleStep> pickleSteps = scenario.getSteps();
                                pickleSteps.forEach(
                                        ps -> {
                                            out.println(" ------- STANDALONE STEPS ------- ");
                                            out.println("ps = " + ps);
                                            out.println("ps.getAstNodeIds() = " + ps.getAstNodeIds());
                                            out.println("ps.getArgument() = " + ps.getArgument());
                                            out.println("ps.getText() = " + ps.getText());
                                            out.println("ps.getId() = " + ps.getId());
                                            out.println("ps.getArgument() = " + ps.getArgument());
                                            out.println("ps.getType() = " + ps.getType());
                                            out.println(" ------- END STANDALONE STEPS ------- ");

                                        }
                                );
                            }
                    );


                }
        );


    }
}