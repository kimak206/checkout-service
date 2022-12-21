package com.service.users.verifier;

import com.service.users.verifier.UsersVerifierTest;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.*;
import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.RestAssured.*;

@SuppressWarnings("rawtypes")
public class UserTest extends UsersVerifierTest {

	@Test
	public void validate_checkoutCreate() throws Exception {
		// given:
			RequestSpecification request = given()
					.header("Content-Type", "application/json")
					.header("Accept", "application/json")
					.body("{\"orderId\":\"5000\",\"userId\":\"1\",\"status\":\"true\",\"orderDate\":\"2020-09-01\"}");

		// when:
			Response response = given().spec(request)
					.post("/checkout");

		// then:
			assertThat(response.statusCode()).isEqualTo(201);
			assertThat(response.header("Content-Type")).matches("application/json.*");

		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['checkoutId']").isEqualTo(1);
			assertThatJson(parsedJson).field("['orderId']").isEqualTo(5000);
			assertThatJson(parsedJson).field("['userId']").isEqualTo(1);
			assertThatJson(parsedJson).field("['status']").isEqualTo(true);
			assertThatJson(parsedJson).field("['orderDate']").isEqualTo("2020-09-01T00:00:00.000+00:00");
	}

	@Test
	public void validate_checkoutGetNotFound() throws Exception {
		// given:
			RequestSpecification request = given()
					.header("Content-Type", "application/json")
					.header("Accept", "application/json");

		// when:
			Response response = given().spec(request)
					.get("/checkout/get/1");

		// then:
			assertThat(response.statusCode()).isEqualTo(404);
			assertThat(response.header("Content-Type")).matches("application/json.*");

		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			String responseBody = response.getBody().asString();
			assertThat(responseBody).isEqualTo("Not Found!");
	}

	@Test
	public void validate_userCreate() throws Exception {
		// given:
			RequestSpecification request = given()
					.header("Content-Type", "application/json")
					.header("Accept", "application/json")
					.body("{\"username\":\"admin\",\"password\":\"123456\"}");

		// when:
			Response response = given().spec(request)
					.post("/users");

		// then:
			assertThat(response.statusCode()).isEqualTo(201);
			assertThat(response.header("Content-Type")).matches("application/json.*");

		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['username']").isEqualTo("admin");
			assertThatJson(parsedJson).field("['password']").isEqualTo("123456");
	}

}
