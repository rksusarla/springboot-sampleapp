package com.acmecorp.sampleapp.endpoints;

import com.acmecorp.sampleapp.domain.Message;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.List;
import java.util.UUID;

/**
 * Created by volen on 2017-07-31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=RANDOM_PORT)
public class MessageEndpointTest {
    @LocalServerPort
    private int serverPort;

    @Before
    public void init(){
        RestAssured.port = serverPort;
    }

    @Test
    public void getMessageHappyPath() throws Exception {
        given().
            auth().basic("admin", "admin").
        when().
            get("/messages").
        then().
            statusCode(200);
    }

    @Test
    public void storeMessageHappyPath() throws Exception {
        Message msg = new Message();
        msg.setTimestamp(System.currentTimeMillis());
        msg.setToUser("vlad");
        msg.setFromUser("dima");
        msg.setId(UUID.randomUUID());
        msg.setText("test text");

        given().
            contentType("application/json").
            auth().basic("admin", "admin").
            body(msg).
        when().
            post("/messages").
        then().
            statusCode(200).
            body("text", equalTo("test text"));

    }

    
    @Test
    public void retrieveMessageHappyPath() throws Exception {
    	storeMessageHappyPath();
    	
        List<Message> messages = given().auth().basic("admin", "admin")
                                .get("/messages").as(List.class);

        assertTrue(messages.size() > 0);
    }



}