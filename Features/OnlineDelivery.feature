 # src/test/resources/api.feature

Feature: API Endpoints

  Background:
    Given the server is running

  # User Endpoints
  Scenario: Get all users
    When I send a GET request to "/users"
    Then the response status should be 200
    And the response should contain a list of users

  Scenario: Register a new user
    When I send a POST request to "/users/register" with the following body:
      """
      {
        "username": "newuser",
        "password": "newpass",
        "email": "newuser@example.com"
      }
      """
    Then the response status should be 201
    And the response should contain the new user details

  Scenario: Login with valid credentials
    When I send a POST request to "/users/login" with the following body:
      """
      {
        "username": "user1",
        "password": "pass1"
      }
      """
    Then the response status should be 200
    And the response should contain a success message

  Scenario: Login with invalid credentials
    When I send a POST request to "/users/login" with the following body:
      """
      {
        "username": "user1",
        "password": "wrongpass"
      }
      """
    Then the response status should be 401
    And the response should contain an error message

  Scenario: Get all restaurants
    When I send a GET request to "/restaurants"
    Then the response status should be 200
    And the response should contain a list of restaurants

  Scenario: Register a new restaurant
    When I send a POST request to "/restaurants" with the following body:
      """
      {
        "name": "New Restaurant",
        "address": "New Address",
        "cuisineType": "New Cuisine",
        "contactInfo": "New Contact"
      }
      """
    Then the response status should be 201
    And the response should contain the new restaurant details

  Scenario: Get a specific restaurant
    When I send a GET request to "/restaurants/1"
    Then the response status should be 200
    And the response should contain the restaurant details

  Scenario: Get all orders
    When I send a GET request to "/orders"
    Then the response status should be 200
    And the response should contain a list of orders

  Scenario: Place a new order
    When I send a POST request to "/orders" with the following body:
      """
      {
        "userId": 1,
        "restaurantId": 1,
        "foodItems": ["Food1", "Food2"],
        "totalPrice": 20.0
      }
      """
    Then the response status should be 201
    And the response should contain the new order details

  Scenario: Get a specific order
    When I send a GET request to "/orders/1"
    Then the response status should be 200
    And the response should contain the order details

  Scenario: Update user profile
    When I send a PUT request to "/users/1" with the following body:
      """
      {
        "username": "updateduser",
        "email": "updateduser@example.com"
      }
      """
    Then the response status should be 200
    And the response should contain the updated user details

  Scenario: Delete a user
    When I send a DELETE request to "/users/1"
    Then the response status should be 204

  Scenario: Search for food items
    When I send a GET request to "/foods/search" with the query parameter "name" as "Food1"
    Then the response status should be 200
    And the response should contain the food items matching the search

  Scenario: Filter food items by cuisine
    When I send a GET request to "/foods/filter" with the query parameter "cuisine" as "Cuisine1"
    Then the response status should be 200
    And the response should contain the food items of the specified cuisine

  Scenario: Get all data
    When I send a GET request to "/data"
    Then the response status should be 200
    And the response should contain all data
 