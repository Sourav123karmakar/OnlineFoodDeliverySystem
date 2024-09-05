Scenario: Register a new restaurant
    When I send a POST request to "/restaurants" with the following body:
      """
      {
        "name": "Zaika Restaurant",
        "address": "Belepole,Howrah",
        "cuisineType": "Chineese Cuisine",
        "contactInfo": "Zaika Contact"
      }
      """
    Then the response status should be 201
    And the response should contain the new restaurant details