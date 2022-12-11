package stepDefination;

import io.restassured.response.ResponseBody;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Steps {
    private int Employee_ID;
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String BASE_URL = "http://localhost:8080";

    private static String token;
    private static Response response;

    @Given("I am an authorized user")
    public void i_am_an_authorized_user() {

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        response = request.body("{ \"userName\":\"" + USERNAME + "\", \"password\":\"" + PASSWORD + "\"}")
                .post("/authenticate");

        String jsonString = response.asString();
        token = jsonString;
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Given("Get all employee")
    public void get_all_employee() {
        RestAssured.baseURI = BASE_URL;

        RequestSpecification request = RestAssured.given();
        response = request.get("/employee/getcontacts");

        ResponseBody body = response.getBody();
        JsonPath js = response.jsonPath();
        int size = js.getInt("EntityPropertyValues.size()");
        System.out.println("size of response" + size);
        Assert.assertTrue("Getting all employee details", size > 0);
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("Adding employee")
    public void add_Employee_Details() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
       response = request.body( "{ \"firstName\":\"Jhon\", \"lastName\":\"One\", \"email\":\"test@one.com\", \"address\":\"One town\" ,\"phoneNumber\":\"465789456\",\"position\":\"IT Manager\" }")
               .post("/employee/add");
       System.out.println("add employee response : "+ response.asString());
       JsonPath jsonPathEvaluator = response.jsonPath();
       Employee_ID  = jsonPathEvaluator.get("id");
       System.out.println("add employee response id : "+ Employee_ID);
    }

    @Then("Check employee is added")
    public void check_employee_is_added() {
        Assert.assertEquals(201, response.getStatusCode());
    }

}