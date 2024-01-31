#Feature: Manage TODO List
#
#  Scenario: Add a task to the TODO list
#    Given the user has an empty TODO list
#    When the user adds a task "Buy groceries" to the TODO list
#    Then the TODO list should contain the task "Buy groceries"
##
##  Scenario: Mark a task as done
##    Given the user has a TODO list with a task "Read a book"
##    When the user marks the task "Read a book" as done
##    Then the TODO list should show the task "Read a book" as done
#
#  Scenario: Remove a task from the TODO list
#    Given the user has a TODO list with tasks:
#      | Write code    |
#      | Exercise      |
#      | Call a friend |
#    When the user removes the task "Write code" from the TODO list
#    Then the TODO list should not contain the task "Write code"
#    And the TODO list should contain the tasks "Exercise" and "Call a friend"
#
#  Scenario: View the TODO list
#    Given the user has a TODO list with tasks "<task>":
#      | Study for exam |
#      | Go for a run   |
#    When the user views the TODO list
#    Then the user should see the tasks "Study for exam" and "Go for a run"
##
##  Scenario: Clear the entire TODO list
##    Given the user has a TODO list with tasks:
##      | Plan vacation     |
##      | Learn a new skill |
##    When the user clears the TODO list
##    Then the TODO list should be empty