package OnlineFoodDelivery;

import io.restassured.RestAssured;
//import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class OnlineFoodDeliveryApiTests {

    private static final String BASE_URL = "http://localhost:3000"; 

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testGetAllUsers() {
        given()
            .when()
            .get("/users")
            .then()
            .statusCode(200)
            .body("$", hasSize(greaterThan(0)));
    }

    @Test
    public void testRegisterUser() {
        String newUserJson = "{ \"username\": \"newuser\", \"password\": \"newpass\", \"email\": \"newuser@example.com\" }";

        given()
            .contentType("application/json")
            .body(newUserJson)
            .when()
            .post("/users/register")
            .then()
            .statusCode(201)
            .body("username", equalTo("newuser"))
            .body("email", equalTo("newuser@example.com"));
    }

    @Test
    public void testLoginSuccess() {
        String loginJson = "{ \"username\": \"newuser\", \"password\": \"newpass\" }";

        given()
            .contentType("application/json")
            .body(loginJson)
            .when()
            .post("/users/login")
            .then()
            .statusCode(200)
            .body("message", equalTo("Login successful"));
    }

    @Test
    public void testLoginFailure() {
        String loginJson = "{ \"username\": \"newuser\", \"password\": \"wrongpass\" }";

        given()
            .contentType("application/json")
            .body(loginJson)
            .when()
            .post("/users/login")
            .then()
            .statusCode(401)
            .body("message", equalTo("Invalid credentials"));
    }

    @Test
    public void testGetAllRestaurants() {
        given()
            .when()
            .get("/restaurants")
            .then()
            .statusCode(200)
            .body("$", hasSize(greaterThan(0)));
    }

    @Test
    public void testCreateRestaurant() {
        String newRestaurantJson = "{ \"name\": \"New Restaurant\", \"address\": \"New Address\", \"cuisineType\": \"New Cuisine\", \"contactInfo\": \"New Contact\" }";

        given()
            .contentType("application/json")
            .body(newRestaurantJson)
            .when()
            .post("/restaurants")
            .then()
            .statusCode(201)
            .body("name", equalTo("New Restaurant"))
            .body("address", equalTo("New Address"))
            .body("cuisineType", equalTo("New Cuisine"))
            .body("contactInfo", equalTo("New Contact"));
    }

    @Test
    public void testGetRestaurantById() {
        int restaurantId = 1;

        given()
            .when()
            .get("/restaurants/" + restaurantId)
            .then()
            .statusCode(200)
            .body("id", equalTo(restaurantId));
    }

    @Test
    public void testCreateOrder() {
        String newOrderJson = "{ \"userId\": 1, \"restaurantId\": 1, \"foodItems\": [\"Food1\", \"Food2\"], \"totalPrice\": 20 }";

        given()
            .contentType("application/json")
            .body(newOrderJson)
            .when()
            .post("/orders")
            .then()
            .statusCode(201)
            .body("userId", equalTo(1))
            .body("restaurantId", equalTo(1))
            .body("foodItems", hasItems("Food1", "Food2"))
            .body("totalPrice", equalTo(20));
    }

    @Test
    public void testGetOrderById() {
        int orderId = 1;

        given()
            .when()
            .get("/orders/" + orderId)
            .then()
            .statusCode(200)
            .body("id", equalTo(orderId));
    }

    @Test
    public void testUpdateUser() {
        String updatedUserJson = "{ \"username\": \"updateduser\", \"email\": \"updateduser@example.com\" }";
        int userId = 1;

        given()
            .contentType("application/json")
            .body(updatedUserJson)
            .when()
            .put("/users/" + userId)
            .then()
            .statusCode(200)
            .body("username", equalTo("updateduser"))
            .body("email", equalTo("updateduser@example.com"));
    }

    @Test
    public void testDeleteUser() {
        int userId = 1;

        given()
            .when()
            .delete("/users/" + userId)
            .then()
            .statusCode(204);
    }

    @Test
    public void testSearchFoods() {
        String queryParam = "Food1";

        given()
            .param("name", queryParam)
            .when()
            .get("/foods/search")
            .then()
            .statusCode(200)
            .body("$", hasSize(greaterThan(0)));
    }

    @Test
    public void testFilterFoodsByCuisine() {
        String cuisine = "Cuisine1";

        given()
            .param("cuisine", cuisine)
            .when()
            .get("/foods/filter")
            .then()
            .statusCode(200)
            .body("$", hasSize(greaterThan(0)));
    }

    @Test
    public void testGetAllData() {
        given()
            .when()
            .get("/data")
            .then()
            .statusCode(200)
            .body("users", notNullValue())
            .body("restaurants", notNullValue())
            .body("orders", notNullValue())
            .body("foods", notNullValue());
    }
}

