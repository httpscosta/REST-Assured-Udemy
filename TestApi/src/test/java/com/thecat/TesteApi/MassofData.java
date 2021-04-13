package com.thecat.TesteApi;

public class MassofData {

	String vote_id;
	String urlRegister = "user/passwordlesssignup";
	String bodyRegister = "{\"email\": \"gabriele.silva.costa@nttdata.com.br\", \"appDescription\":  \"test cat api\"}";
	String urlVotes = "votes/";
	String bodyVotes = "{\"image_id\": \"bs9\", \"value\": \"true\", \"sub_id\": \"demo-8cfeda\"}";
	String bodyFavourites = "{\r\n" + "  \"image_id\": \"9ccXTANkb\",\r\n" + "  \"sub_id\": \"your-user-1234\"\r\n" + "}";
}
