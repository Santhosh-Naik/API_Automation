import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

import java.time.Duration;

import static io.restassured.RestAssured.given;

public class Telco_Api_SendSMS {


    @Test
    public static void sendRequestForSMS() {


        sendRequestBody("10");

    }


    public static void sendRequestBody(String series) {

        for (int i = 0; i <=10; i++) {

            //MTN = 2348060817482; Other = 2349091751329; Airtel = 2347019781418
            RestAssured.baseURI = "http://url";
            String request = "{\n" +
                    "  \"phoneNumber\": \"2348060817482\",\n" +
                    "  \"message\": \"Test message from santhosh for MTN-- " + series + i + "\",\n" +
                    "  \"smsReference\": \"TestMTN_" + series + i + "\"\n" +
                    "}";
            Response resp = given().
                    header("Content-Type", "application/json").
                    body(request).
                    when().
                    post("/api/Notification/SendSms").
                    then().log().all().assertThat().statusCode(200).
                    extract().response();

        }

    }

    public static String getResponse(String series) {

        String response = "";
        for (int i = 0; i <= 100; i++) {

            response = "{\n" +
                    "  \"smsReference\": \"T_" + series + i + "\"\n" +
                    "}";
        }

        return response;

    }

}


 /*  RestAssured.baseURI = "http://45.35.48.186";

        Response resp = given().
                header("Content-Type", "application/json").
                body(sendRequestBody("5")).
                when().
                post("/api/Notification/SendSms").
                then().assertThat().statusCode(200).
                extract().response();

    */
