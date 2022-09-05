import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Telco_Api_VerifySent_SMS {


    @Test
    public static void verifyResponseOfSentSMS() {

        getResponse("100");

    }


    public static void getResponse(String series) {

        for (int i = 0; i <= 10; i++) {

            RestAssured.baseURI = "http://url";
            String response = "{\n" +
                    "  \"smsReference\": \"TestAirtel_" + series + i + "\"\n" +
                    "}";
            String resp = given().
                    header("Content-Type", "application/json").
                    body(response).
                    when().
                    post("/api/Notification/SmsQuery").
                    then().assertThat().statusCode(200).
                    extract().response().asString();
            System.out.println(resp);


          /*  JsonPath js = new JsonPath(resp);
            String ref_id = js.get("smsReference");
            System.out.println("ref_id: "+ref_id);
            String sent_SMS = js.get("message");
            System.out.println("sent_SMS: "+sent_SMS);
            String sent_SMS_Status = js.get("sent");
            System.out.println("sent_SMS_Status: "+sent_SMS_Status);
*/
        }

    }

}
