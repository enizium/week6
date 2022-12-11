package stepDefination;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Steps {
    private static final String Employee_ID = "43";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String BASE_URL = "http://localhost:8080";

    private static String token;
    private static Response response;
    private static String jsonString;
    private static String employeeId;


    @Given("I am an authorized user")
    public void i_am_an_authorized_user() {

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        response = request.body("{ \"userName\":\"" + USERNAME + "\", \"password\":\"" + PASSWORD + "\"}")
                .post("/authenticate");

        String jsonString = response.asString();
        token = JsonPath.from(jsonString).get("token");
        System.out.println("Token: "+ token);

    }

    @Given("Get all employee")
    public void get_all_employee() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get("/employee/getcontacts");

        jsonString = response.asString();
        List<Map<String, String>> employee = JsonPath.from(jsonString).get("employee");
        Assert.assertTrue(employee.size() > 0);

        employeeId = employee.get(0).get("firstName");
    }

    @When("Admin request to get all Employee details")
    public void admin_request_to_get_all_employee_details() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");
       response = request.body( "{ \"firstName\":\"Test\", \"lastName\":\"One\", \"email\":\"test@one.com\", \"address\":\"One town\" \"phoneNumber\":\"465789456\",\"position\":\"IT Manager\" }").post("/employee/getcontacts");
    }

    @Then("Check employee is added")
    public void check_employee_is_added() {
        Assert.assertEquals(201, response.getStatusCode());
    }
}