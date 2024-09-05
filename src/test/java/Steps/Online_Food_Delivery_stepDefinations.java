package Steps;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import static org.junit.Assert.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
//import io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

//import java.io.IOException;

public class Online_Food_Delivery_stepDefinations {

    private Response response;
    private static final String BASE_URL = "http://localhost:3000"; // Change this to your server URL

    @Given("the server is running")
    public void the_server_is_running() {
        // Assuming server is running; otherwise, add code to start it
        RestAssured.baseURI = BASE_URL;
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        response = given()
                    .when()
                    .get(endpoint);
    }

    @When("I send a POST request to {string} with the following body:")
    public void i_send_a_post_request_to_with_the_following_body(String endpoint, String body) {
        response = given()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .when()
                    .post(endpoint);
    }

    @When("I send a PUT request to {string} with the following body:")
    public void i_send_a_put_request_to_with_the_following_body(String endpoint, String body) {
        response = given()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .when()
                    .put(endpoint);
    }

    @When("I send a DELETE request to {string}")
    public void i_send_a_delete_request_to(String endpoint) {
        response = given()
                    .when()
                    .delete(endpoint);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response should contain a list of users")
    public void the_response_should_contain_a_list_of_users() {
        assertTrue(response.getBody().jsonPath().getList("$").size() > 0);
    }

    @Then("the response should contain the new user details")
    public void the_response_should_contain_the_new_user_details() {
        assertNotNull(response.getBody().jsonPath().getString("username"));
        assertNotNull(response.getBody().jsonPath().getString("email"));
    }

    @Then("the response should contain a success message")
    public void the_response_should_contain_a_success_message() {
        assertEquals("Login successful", response.getBody().jsonPath().getString("message"));
    }

    @Then("the response should contain an error message")
    public void the_response_should_contain_an_error_message() {
        assertEquals("Invalid credentials", response.getBody().jsonPath().getString("message"));
    }

    @Then("the response should contain a list of restaurants")
    public void the_response_should_contain_a_list_of_restaurants() {
        assertTrue(response.getBody().jsonPath().getList("$").size() > 0);
    }

    @Then("the response should contain the new restaurant details")
    public void the_response_should_contain_the_new_restaurant_details() {
        assertNotNull(response.getBody().jsonPath().getString("name"));
        assertNotNull(response.getBody().jsonPath().getString("address"));
        assertNotNull(response.getBody().jsonPath().getString("cuisineType"));
        assertNotNull(response.getBody().jsonPath().getString("contactInfo"));
    }

    @Then("the response should contain the restaurant details")
    public void the_response_should_contain_the_restaurant_details() {
        assertNotNull(response.getBody().jsonPath().getString("id"));
        assertNotNull(response.getBody().jsonPath().getString("name"));
        assertNotNull(response.getBody().jsonPath().getString("address"));
        assertNotNull(response.getBody().jsonPath().getString("cuisineType"));
        assertNotNull(response.getBody().jsonPath().getString("contactInfo"));
    }

    @Then("the response should contain a list of orders")
    public void the_response_should_contain_a_list_of_orders() {
        assertTrue(response.getBody().jsonPath().getList("$").size() > 0);
    }

    @Then("the response should contain the new order details")
    public void the_response_should_contain_the_new_order_details() {
        assertNotNull(response.getBody().jsonPath().getString("userId"));
        assertNotNull(response.getBody().jsonPath().getString("restaurantId"));
        assertNotNull(response.getBody().jsonPath().getList("foodItems"));
        assertNotNull(response.getBody().jsonPath().getString("totalPrice"));
    }

    @Then("the response should contain the order details")
    public void the_response_should_contain_the_order_details() {
        assertNotNull(response.getBody().jsonPath().getString("id"));
        assertNotNull(response.getBody().jsonPath().getString("userId"));
        assertNotNull(response.getBody().jsonPath().getString("restaurantId"));
        assertNotNull(response.getBody().jsonPath().getList("foodItems"));
        assertNotNull(response.getBody().jsonPath().getString("totalPrice"));
    }

    @Then("the response should contain the updated user details")
    public void the_response_should_contain_the_updated_user_details() {
        assertNotNull(response.getBody().jsonPath().getString("username"));
        assertNotNull(response.getBody().jsonPath().getString("email"));
    }

    @Then("the response should contain the food items matching the search")
    public void the_response_should_contain_the_food_items_matching_the_search() {
        assertTrue(response.getBody().jsonPath().getList("$").size() > 0);
    }

    @Then("the response should contain the food items of the specified cuisine")
    public void the_response_should_contain_the_food_items_of_the_specified_cuisine() {
        assertTrue(response.getBody().jsonPath().getList("$").size() > 0);
    }

    @Then("the response should contain all data")
    public void the_response_should_contain_all_data() {
        assertNotNull(response.getBody().jsonPath().getList("users"));
        assertNotNull(response.getBody().jsonPath().getList("restaurants"));
        assertNotNull(response.getBody().jsonPath().getList("orders"));
        assertNotNull(response.getBody().jsonPath().getList("foods"));
    }
    @When("I send a GET request to {string} with the query parameter {string} as {string}")
    public void i_send_a_get_request_to_with_query_parameter_as(String endpoint, String queryParam, String queryValue) {
        response = given()
                    .param(queryParam, queryValue)
                    .when()
                    .get(endpoint);
    }
}
