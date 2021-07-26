
package com.cucumber.framework.stepdefinition;

import java.io.File;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.testng.Assert;

import com.cucumber.framework.helper.PageObject.homepage.HomePage;
import com.cucumber.framework.utility.ResourceHelper;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class PostCommentStepDfn {

	private HomePage hPage;
	private Scenario scenario;

	@Before
	public void before(Scenario scenario) {
		this.scenario = scenario;
	}

	@Given("^: I am at the API home page$")
	public void _i_am_at_the_API_home_page() throws Throwable {
		Response response = RestAssured.get("https://jsonplaceholder.typicode.com");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 200);
	}
    
	@Then("^: I get all the Posts$")
	public void _i_get_all_the_posts() throws Throwable {
	}
	
	@And("^: I validate posts response code and valid json content$")
	public void posts_validate() throws Throwable {
		Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts");
		int code = response.getStatusCode();
		Assert.assertEquals(code, 200);
		scenario.write("Status code 200 matched");
		Assert.assertEquals(response.asString().isEmpty(), false);
		scenario.write("posts response is not empty");
		
		Post[] postList = response.then().extract().as(Post[].class);
		Set<String> idSet = new HashSet<String>();
		for (Post post : postList) {
			idSet.add(post.getId());
		}
		
		for (String temp : idSet) {
			Response response1 = RestAssured.get("https://jsonplaceholder.typicode.com/comments?postId=" + temp);
			Assert.assertEquals(!(response1.asString().isEmpty()), true, "Comments response empty");
			Comment [] commentList = response1.then().extract().as(Comment[].class);
			Assert.assertEquals(commentList.length > 0, true, "Comments response has 0 elements");
		}
		scenario.write("All posts have at least one comment present");
		Properties prop = new Properties();
		prop.load(ResourceHelper.getResourcePathInputStream("/schema/postValidation.json"));
		File schema = new File(
				"C:/Users/gupta/Desktop/Assignment/VanMoofAssignment/src/test/java/com/cucumber/framework/stepdefinition/postValidation.json");
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schema));
		scenario.write("posts content is a valid JSON");
	}

	
	@Then("^: I validate posts reference by the comments exist$")
	public void _i_validate_post_reference_by_comment_exist() throws Throwable {
		Response response = RestAssured.get("https://jsonplaceholder.typicode.com/comments");
		Comment[] commentList = response.then().extract().as(Comment[].class);

		Set<String> postIdSet = new HashSet<String>();
		for (Comment comment : commentList) {
			postIdSet.add(comment.getPostId());
		}
		for (String postId : postIdSet) {
			Response postsResponse = RestAssured.get("https://jsonplaceholder.typicode.com/posts/" + postId);
			Assert.assertEquals(!(postsResponse.asString().isEmpty()), true, "Post does not exist");
            
		}
		scenario.write("Posts referenced by the comment exist");
	}
}