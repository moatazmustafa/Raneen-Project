package Tests;

import BaseApi.BaseApiTest;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class ApiCancelReasonsTest extends BaseApiTest {
    private static final Logger log = LoggerFactory.getLogger(ApiCancelReasonsTest.class);

    @Test(priority = 1)
    public void testCancelReasons_Valid_AR() {
        log.info("✅ Testing Cancel Reason API - Arabic Store");

        Response response = withDefaultHeaders()
                .queryParam("storeId", 2)
                .get("/ar/mobileapi/v2_customer/CancelReasonData");

        response.prettyPrint(); // ✅ Prints JSON body

        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo(""))
                .body("reasons.size()", equalTo(6))
                .body("reasons[0].reason", equalTo("لقد اشتريت المنتج الخطأ"))
                .body("reasons[1].reason", equalTo("لقد وجدت بديلاً اوفر"))
                .body("reasons[2].reason", equalTo("لقد قمت بطلب مكرر"))
                .body("reasons[3].reason", equalTo("لقد تلقيت ملاحظات سلبية عن المنتج بعد الشراء"))
                .body("reasons[4].reason", equalTo("التوصيل يستغرق وقتًا طويلاً"))
                .body("reasons[5].reason", equalTo("لقد غيرت رأيي"));
    }

    @Test(priority = 2)
    public void testCancelReasons_Valid_EN() {
        log.info("✅ Testing Cancel Reason API - English Store");

        Response response = withDefaultHeaders()
                .queryParam("storeId", 4)
                .get("/en/mobileapi/v2_customer/CancelReasonData");

        response.prettyPrint(); // ✅ Prints JSON body

        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo(""))
                .body("reasons.size()", equalTo(6))
                .body("reasons[0].reason", equalTo("I bought the wrong item(s) "))
                .body("reasons[1].reason", equalTo("I found a cheaper alternative"))
                .body("reasons[2].reason", equalTo("I placed a duplicate order"))
                .body("reasons[3].reason", equalTo("I received negative feedback about the item after purchase"))
                .body("reasons[4].reason", equalTo("Delivery takes too long"))
                .body("reasons[5].reason", equalTo("I have changed my mind"));
    }

    @Test(priority = 3)
    public void testCancelReasons_InvalidStore_AR() {
        log.info("🚫 Testing Cancel Reason API - Invalid Store (AR)");

        Response response = withDefaultHeaders()
                .queryParam("storeId", 999)
                .get("/ar/mobileapi/v2_customer/CancelReasonData");

        response.prettyPrint(); // ✅ Prints JSON body

        response.then().statusCode(200)
                .body("success", equalTo(false))
                .body("message", equalTo("لم يتم العثور على المتجر الذي تم طلبه. تحقق من المتجر وحاول مرة أخرى."));
    }

    @Test(priority = 4)
    public void testCancelReasons_InvalidStore_EN() {
        log.info("🚫 Testing Cancel Reason API - Invalid Store (EN)");

        Response response = withDefaultHeaders()
                .queryParam("storeId", 999)
                .get("/en/mobileapi/v2_customer/CancelReasonData");

        response.prettyPrint(); // ✅ Prints JSON body

        response.then().statusCode(200)
                .body("success", equalTo(false))
                .body("message", equalTo("The store that was requested wasn't found. Verify the store and try again."));
    }
}
