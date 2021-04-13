package com.thecat.TesteApi;

import org.junit.BeforeClass;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestApi extends MassofData{
	
	@BeforeClass
	public static void urlBase() {
		RestAssured.baseURI = "https://api.thecatapi.com/v1/";
	}
	
	@Test
	public void register() {
		
		//DADO QUE GIVEN  //QUANDO ESTIVER COOM WHEN  //ENTÃO THEN
		Response response = given().contentType("application/json").body(bodyRegister).when().post(urlRegister);
		
		//VERIFICA SE A MESSAGE É "SUCCESS" E DEPOIS VERIFICA O STATUS DE 200
		validation(response);
		
		}
	
	@Test
	public void votes() {
		//DADO QUE GIVEN  //QUANDO ESTIVER COOM WHEN  //ENTÃO THEN
		Response response = given().contentType("application/json").body(bodyVotes).
		when().post(urlVotes);
		
		validation(response);

		//PEGAR O ID ATRAVÉS DO JSONPATH()
		String id = response.jsonPath().getString("id");
		vote_id = id;
		System.out.println("ID => " + id);
		
	}
	
	public void deleteVotes() {
		votes();
		deleteVote();
	}
	
	@Test
	public void deleteVote() {
		
		String url = "https://api.thecatapi.com/v1/votes/{vote_id}";
		
		//DADO QUE GIVEN  //QUANDO ESTIVER COOM WHEN  //ENTÃO THEN
		Response response = given().contentType("application/json").header("x-api-key", "5ff1b80a-7870-4ee9-bd75-bdab5f76ccf7")
							.pathParam("vote_id", vote_id).when().delete(url);
		
		validation(response);
	}
	
		//DESAFIO FAVORITAR UMA IMAGEM E DEPOIS DELETAR ESSE DADO FAVORITADO
	public void favourites() {
		
		//DADO QUE GIVEN  //QUANDO ESTIVER COOM WHEN  //ENTÃO THEN
		Response response = given().contentType("application/json").header("x-api-key", "5ff1b80a-7870-4ee9-bd75-bdab5f76ccf7").body(bodyFavourites).
							when().post("https://api.thecatapi.com/v1/favourites");
		
		String id = response.jsonPath().getString("id");
		vote_id = id;
		
		validation(response);
	}
	
	public void disfavor() {
		Response response = given().contentType("application/json").header("x-api-key", "5ff1b80a-7870-4ee9-bd75-bdab5f76ccf7").pathParam("favourite_id", vote_id)
							.when().delete("https://api.thecatapi.com/v1/favourites/{favourite_id}");
		validation(response);
		
	}
	
	@Test
	public void disfavorFavourites() {
		favourites();
		disfavor();
	}
	
	public void validation(Response response) {
		response.then().body("message",containsString("SUCCESS")).statusCode(200);
		System.out.println("Retorno da API => " + response.body().asString());
		System.out.println("---------------------------------------------------");
		
	}
	
}
