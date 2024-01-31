package todo;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static java.lang.System.out;

public class TODOStepdefs {
    @Given("^the user has an empty TODO list$")
    public void theUserHasAnEmptyTODOList() {
    }

    @When("the user adds a task {string} to the TODO list")
    public void theUserAddsATaskToTheTODOList(String arg0) {
    }

    @Then("the TODO list should contain the task {string}")
    public void theTODOListShouldContainTheTask(String arg0) {
        
    }

    @Given("the user has a TODO list with tasks:")
    public void theUserHasATODOListWithTasks(DataTable dataTable) {
        dataTable.asList().forEach(out::println);
    }

    @When("the user removes the task {string} from the TODO list")
    public void theUserRemovesTheTaskFromTheTODOList(String arg0) {


    }

    @Then("the TODO list should not contain the task {string}")
    public void theTODOListShouldNotContainTheTask(String arg0) {
        
    }

    @And("the TODO list should contain the tasks {string} and {string}")
    public void theTODOListShouldContainTheTasksAnd(String arg0, String arg1) {
        
    }

    @When("the user views the TODO list")
    public void theUserViewsTheTODOList() {
        
    }

    @Then("the user should see the tasks {string} and {string}")
    public void theUserShouldSeeTheTasksAnd(String arg0, String arg1) {
    }

}
