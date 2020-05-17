@Example
Feature: User would like to get examples
  Background:
    Given the following examples exists in the library
      | code | description                 |
      | 1    | Twinkle twinkle little star |
      | 2    | Johnny Johnny Yes Papa      |

  Scenario: User should be able to get all examples
    When user requests for all examples
    Then the user gets the following examples
      | code | description                 |
      | 1    | Twinkle twinkle little star |
      | 2    | Johnny Johnny Yes Papa      |

  Scenario: User should be able to get examples by code
    When user requests for examples by code "1"
    Then the user gets the following examples
      | code | description                 |
      | 1    | Twinkle twinkle little star |

  Scenario: User should get an appropriate NOT FOUND message while trying get examples by an invalid code
    When user requests for examples by id "10000" that does not exists
    Then the user gets an exception "Example with code 10000 does not exist"