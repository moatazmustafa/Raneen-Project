package Tests;

import BaseApi.BaseApiTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class ApiWishlistTest extends BaseApiTest {
    private static final Logger log = LoggerFactory.getLogger(ApiWishlistTest.class);

    String validToken = "********"; // Update with real token dynamically later
    int storeIdAR = 2;  // AR store ID
    int storeIdEN = 4;  // EN store ID
    int productId = 413176;
    int itemIdAR;
    int secItemIdAR;
    int itemIdEN;
    int secItemIdEN;
    String cartItemIdAR;
    String CartItemIdEN;

    @Test(priority = 1)
    public void add_To_Wishlist_AR() {
        log.info("Adding product to wishlist (AR store)...");
        Response response = withDefaultHeaders()
                .formParam("storeId", storeIdAR)
                .formParam("productId", productId)
                .formParam("customerToken", validToken)
                .post("/ar/mobileapi/catalog/addtowishlist");

        response.prettyPrint();

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("message", containsString("تمت إضافة المنتج إلى قائمة الرغبات بنجاح."));

        JsonPath json = response.jsonPath();
        itemIdAR = json.getInt("itemId");
        log.info("Captured ItemId: {}", itemIdAR);
    }
    @Test(priority = 2)
    public void get_Wishlist_AR() {
        log.info("Getting wishlist items (AR store)...");
        Response response = withDefaultHeaders()
                .formParam("storeId", storeIdAR)
                .formParam("customerToken", validToken)
                .get("/ar/mobileapi/customer/wishlist");

        response.prettyPrint();

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("wishList", not(empty()))
                .body("wishList[0].productId", containsString("413176"))
                .body("wishList[0].sku", equalTo("test33"))
                .body("wishList[0].name", equalTo("test33"))
                .body("wishList[0].formattedFinalPrice", containsString("جنيه"))
                .body("wishList[0].brandName", equalTo("تورنيدو"))
                .body("wishList[0].isInWishlist", equalTo(true))
                .body("wishList[0].availability", equalTo("In stock"));
    }
    @Test(priority = 3)
    public void wishList_To_Cart_AR() {
        log.info("Moving wishlist item to cart (AR store)...");
        Response response = withDefaultHeaders()
                .formParam("itemId", itemIdAR)
                .formParam("storeId", productId)
                .formParam("storeId", storeIdAR)
                .formParam("customerToken", validToken)
                .post("/ar/mobileapi/customer/wishlisttocart");

        response.prettyPrint();

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("message", containsString("تم نقل المنتج (المنتجات) إلى سلة التسوق بنجاح."));

    }
    @Test(priority = 4)
    public void cart_Details_AR() {
        log.info("Adding product to wishlist (AR store)...");
        Response response = withDefaultHeaders()
                .formParam("storeId", storeIdAR)
                .formParam("customerToken", validToken)
                .get("/ar/mobileapi/checkout/cartdetails");

        response.prettyPrint();

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("items", not(empty()))
                .body("items[0].sku", equalTo("test33"))
                .body("items[0].price", equalTo(500))
                .body("items[0].productId", equalTo("413176"))
                .body("items[0].subTotal", containsString("جنيه"))
                .body("isCheckoutAllowed", equalTo(true));

        JsonPath json = response.jsonPath();
        cartItemIdAR = json.getString("items[0].id");
        log.info("Captured cartItemId: {}", cartItemIdAR);
    }
    @Test(priority = 5)
    public void wishList_From_Cart_AR() {
        log.info("Moving cart item to wishlist (AR store)...");

        Response response = withDefaultHeaders()
                .formParam("itemId", cartItemIdAR)
                .formParam("storeId", storeIdAR)
                .formParam("customerToken", validToken)
                .post("/ar/mobileapi/checkout/wishlistfromcart");

        response.prettyPrint();

        log.info("Starting wishlist from cart assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("message", containsString("تم نقل المنتج الى قائمة المنتجات المفضلة"));
    }


@Test(priority = 6)
public void remove_From_WishList_AR() {
            log.info("Refreshing wishlist to get latest item ID before removal...");

        Response wishlistResponse = withDefaultHeaders()
                .formParam("storeId", storeIdAR)
                .formParam("customerToken", validToken)
                .get("/ar/mobileapi/customer/wishlist");

        wishlistResponse.prettyPrint();

    log.info("Starting field assertions...");
        wishlistResponse.then().statusCode(200)
                .body("success", equalTo(true))
                .body("wishList", not(empty()));

        JsonPath json = wishlistResponse.jsonPath();
        secItemIdAR = json.getInt("wishList[0].wishlistItemId");


        log.info("Removing item from wishlist (AR store) with itemId: {}", secItemIdAR);
        Response response = withDefaultHeaders()
                .queryParam("storeId", storeIdAR)
                .queryParam("itemId", secItemIdAR) // ✅ Use wishlistItemId here
                .queryParam("customerToken", validToken)
                .delete("/ar/mobileapi/customer/removefromwishlist");

        response.prettyPrint();

    log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("message", containsString("تم حذف المنتج بنجاح من قائمة الرغبات."));
    }

    @Test(priority = 7)
    public void add_To_Wishlist_EN() {
        log.info("Adding product to wishlist (EN store)...");
        Response response = withDefaultHeaders()
                .formParam("storeId", storeIdEN)
                .formParam("productId", productId)
                .formParam("customerToken", validToken)
                .post("/en/mobileapi/catalog/addtowishlist");

        response.prettyPrint();

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("message", containsString("Item added to wishlist Successfully."));

        JsonPath json = response.jsonPath();
        itemIdEN = json.getInt("itemId");
        log.info("Captured ItemId: {}", itemIdEN);
    }
    @Test(priority = 8)
    public void get_Wishlist_EN() {
        log.info("Getting wishlist items (EN store)...");
        Response response = withDefaultHeaders()
                .formParam("storeId", storeIdEN)
                .formParam("customerToken", validToken)
                .get("/en/mobileapi/customer/wishlist");

        response.prettyPrint();

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("wishList", not(empty()))
                .body("wishList[0].productId", containsString("413176"))
                .body("wishList[0].sku", equalTo("test33"))
                .body("wishList[0].name", equalTo("test33"))
                .body("wishList[0].formattedFinalPrice", containsString("EGP"))
                .body("wishList[0].brandName", equalTo("Tornado"))
                .body("wishList[0].isInWishlist", equalTo(true))
                .body("wishList[0].availability", equalTo("In stock"));
    }
    @Test(priority = 9)
    public void wishList_To_Cart_EN() {
        log.info("Moving wishlist item to cart (EN store)...");
        Response response = withDefaultHeaders()
                .formParam("itemId", itemIdEN)
                .formParam("storeId", productId)
                .formParam("storeId", storeIdEN)
                .formParam("customerToken", validToken)
                .post("/en/mobileapi/customer/wishlisttocart");

        response.prettyPrint();

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("message", containsString("Product(s) has successfully moved to cart."));
    }
    @Test(priority = 10)
    public void cart_Details_EN() {
        log.info("Adding product to wishlist (EN store)...");
        Response response = withDefaultHeaders()
                .formParam("storeId", storeIdEN)
                .formParam("customerToken", validToken)
                .get("/en/mobileapi/checkout/cartdetails");

        response.prettyPrint();
        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("items", not(empty()))
                .body("items[0].sku", equalTo("test33"))
                .body("items[0].price", equalTo(500))
                .body("items[0].productId", equalTo("413176"))
                .body("items[0].subTotal", containsString("EGP"))
                .body("isCheckoutAllowed", equalTo(true));

        JsonPath json = response.jsonPath();
        CartItemIdEN = json.getString("items[0].id");
        log.info("Captured cartItemId: {}", CartItemIdEN);
    }
    @Test(priority = 11)
    public void wishList_From_Cart_EN() {
        log.info("Moving cart item to wishlist (EN store)...");

        Response response = withDefaultHeaders()
                .formParam("itemId", CartItemIdEN)
                .formParam("storeId", storeIdEN)
                .formParam("customerToken", validToken)
                .post("/en/mobileapi/checkout/wishlistfromcart");

        response.prettyPrint();

        log.info("Starting wishlist from cart assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("message", containsString("Item has been moved to Wish list Menu"));
    }


    @Test(priority = 12)
    public void remove_From_WishList_EN() {
        log.info("Refreshing wishlist to get latest item ID before removal...");

        Response wishlistResponse = withDefaultHeaders()
                .formParam("storeId", storeIdEN)
                .formParam("customerToken", validToken)
                .get("/en/mobileapi/customer/wishlist");

        wishlistResponse.prettyPrint();

        log.info("Starting field assertions...");
        wishlistResponse.then().statusCode(200)
                .body("success", equalTo(true))
                .body("wishList", not(empty()));

        JsonPath json = wishlistResponse.jsonPath();
        secItemIdEN = json.getInt("wishList[0].wishlistItemId");

        log.info("Removing item from wishlist (EN store) with itemId: {}", secItemIdEN);
        Response response = withDefaultHeaders()
                .queryParam("storeId", storeIdEN)
                .queryParam("itemId", secItemIdEN) // ✅ Use wishlistItemId here
                .queryParam("customerToken", validToken)
                .delete("/en/mobileapi/customer/removefromwishlist");

        response.prettyPrint();

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("message", containsString("Item successfully deleted from wishlist."));
    }
}
