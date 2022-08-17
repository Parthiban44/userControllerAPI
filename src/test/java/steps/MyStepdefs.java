package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.User;
import org.json.simple.JSONObject;
import org.testng.Assert;
import utils.Endpoints;
import utils.TestNGListener;

import static io.restassured.RestAssured.given;


public class MyStepdefs {
    User user;
    JsonPath jsonpath;
    JSONObject jsonObject;
    ObjectMapper objectMapper=new ObjectMapper();
    Response response;
    User responseUser;


    @Given("user details")
    public void userDetails() {

        jsonObject = (JSONObject) TestNGListener.data.get("createRequest");
    }

    @When("creating a user")
    public void creatingAUser() throws JsonProcessingException {

        user= new User((String) jsonObject.get("name"),
                (String)jsonObject.get("address"),
                (Long)jsonObject.get("marks"));
        response=given().body(user)
                .when().post(Endpoints.USER_ENDPOINTS1)
                .then().statusCode(200).extract().response();
        responseUser=objectMapper.readValue(response.asString(), User.class);
    }

    @Then("user must be created")
    public void userMustBeCreated() throws JsonProcessingException  {
         //responseUser=objectMapper.readValue(response.asString(),UserController.class);
        Assert.assertEquals(user.getName(),responseUser.getName());
        Assert.assertEquals(user.getAddress(),responseUser.getAddress());
        Assert.assertEquals(user.getMarks(),responseUser.getMarks());
    }


    @When("creating a user with no marks")
    public void creatingAUserWithNoMarks() throws JsonProcessingException {
        user= new User((String) jsonObject.get("name"),
                (String)jsonObject.get("address"),
                0);
        response=given().body(user)
                .when().post(Endpoints.USER_ENDPOINTS1)
                .then().statusCode(200).extract().response();
        responseUser=objectMapper.readValue(response.asString(), User.class);
    }

    @Then("user can be created with zero marks")
    public void userCanBeCreatedWithZeroMarks() {
        Assert.assertEquals(user.getName(),responseUser.getName());
        Assert.assertEquals(user.getAddress(),responseUser.getAddress());
    }

    @When("creating a user with no name")
    public void creatingAUserWithNoName() {
        user= new User(null,
                (String)jsonObject.get("address"),
                (Long)jsonObject.get("marks"));
        response=given().body(user)
                .when().post(Endpoints.USER_ENDPOINTS1)
                .then().statusCode(400).extract().response();
    }

    @Then("Name is required error message thrown")
    public void nameIsRequiredErrorMessageThrown() {
        jsonpath = new JsonPath(response.asString());
        Assert.assertEquals(jsonpath.getString("message"),"Name is required");
    }

    @When("creating a user with no address")
    public void creatingAUserWithNoAddress() {
        user= new User((String) jsonObject.get("name"),
                null,
                (Long)jsonObject.get("marks"));
        response=given().body(user)
                .when().post(Endpoints.USER_ENDPOINTS1)
                .then().statusCode(400).extract().response();
    }


    @Then("Address is required error message thrown")
    public void addressIsRequiredErrorMessageThrown() {
        jsonpath = new JsonPath(response.asString());
        Assert.assertEquals(jsonpath.getString("message"),"Address is required");
    }


    @When("Updating a user name")
    public void updatingAUserName() throws JsonProcessingException {
        jsonObject= (JSONObject) TestNGListener.data.get("createRequest");
        user = new User((String) jsonObject.get("name"),
                (String) jsonObject.get("address"),
                (Long) jsonObject.get("marks"));

        response = given()
                .body(user)
                .when().post(Endpoints.USER_ENDPOINTS1)
                .then()
                .statusCode(200).extract().response();
        objectMapper = new ObjectMapper();
        responseUser = objectMapper.readValue(response.asString(), User.class);

        jsonpath = new JsonPath(response.asString());

    }

    @Then("User name must be updated")
    public void userNameMustBeUpdated() throws JsonProcessingException {
        jsonObject= (JSONObject) TestNGListener.data.get("updateRequest");
        user =  new User(jsonpath.getInt("id"),(String) jsonObject.get("name"),
                (String) jsonObject.get("address"),
                (Long) jsonObject.get("marks"));

        Response putresponse = given()
                .body(user)
                .when().put(Endpoints.USER_ENDPOINTS2)
                .then()
                .statusCode(200).extract().response();
        jsonpath = new JsonPath(putresponse.asString());
        objectMapper = new ObjectMapper();
        responseUser = objectMapper.readValue(putresponse.asString(), User.class);
        Assert.assertEquals(user.getName(),responseUser.getName());
        Assert.assertEquals(user.getId(),responseUser.getId());
    }

    @When("updating a user with address")
    public void creatingAUserWithAddress() throws JsonProcessingException {
        jsonObject= (JSONObject) TestNGListener.data.get("createRequest");
        user = new User((String) jsonObject.get("name"),
                (String) jsonObject.get("address"),
                (Long) jsonObject.get("marks"));

        response = given()
                .body(user)
                .when().post(Endpoints.USER_ENDPOINTS1)
                .then()
                .statusCode(200).extract().response();
        objectMapper = new ObjectMapper();
        responseUser = objectMapper.readValue(response.asString(), User.class);

        jsonpath = new JsonPath(response.asString());
    }

    @Then("user address must be updated")
    public void userAddressMustBeUpdated() throws JsonProcessingException {
        jsonObject= (JSONObject) TestNGListener.data.get("updateRequest");
        user =  new User(jsonpath.getInt("id"),(String) jsonObject.get("name"),
                (String) jsonObject.get("address"),
                (Long) jsonObject.get("marks"));

        Response putresponse = given()
                .body(user)
                .when().put(Endpoints.USER_ENDPOINTS2)
                .then()
                .statusCode(200).extract().response();
        jsonpath = new JsonPath(putresponse.asString());
        objectMapper = new ObjectMapper();
        responseUser = objectMapper.readValue(putresponse.asString(), User.class);
        Assert.assertEquals(user.getAddress(),responseUser.getAddress());
        Assert.assertEquals(user.getId(),responseUser.getId());
    }
    @When("updating users marks")
    public void updatingUsersMarks() throws JsonProcessingException {
        jsonObject= (JSONObject) TestNGListener.data.get("createRequest");
        user = new User((String) jsonObject.get("name"),
                (String) jsonObject.get("address"),
                (Long) jsonObject.get("marks"));

        response = given()
                .body(user)
                .when().post(Endpoints.USER_ENDPOINTS1)
                .then()
                .statusCode(200).extract().response();
        objectMapper = new ObjectMapper();
        responseUser = objectMapper.readValue(response.asString(), User.class);

        jsonpath = new JsonPath(response.asString());
    }

    @Then("user marks must be updated")
    public void userMarksMustBeUpdated() throws JsonProcessingException {
        jsonObject= (JSONObject) TestNGListener.data.get("updateRequest");
        user =  new User(jsonpath.getInt("id"),(String) jsonObject.get("name"),
                (String) jsonObject.get("address"),
                (Long) jsonObject.get("marks"));

        Response putresponse = given()
                .body(user)
                .when().put(Endpoints.USER_ENDPOINTS2)
                .then()
                .statusCode(200).extract().response();
        jsonpath = new JsonPath(putresponse.asString());
        objectMapper = new ObjectMapper();
        responseUser = objectMapper.readValue(putresponse.asString(), User.class);
        Assert.assertEquals(user.getMarks(),responseUser.getMarks());
        Assert.assertEquals(user.getId(),responseUser.getId());
    }


}