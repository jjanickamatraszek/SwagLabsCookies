import com.qaprosoft.carina.core.foundation.IAbstractTest;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CookiesTest implements IAbstractTest {

    @Test
    public void loginWithCookie() {
        String cookieName = "session-username";
        String cookieValue = "standard_user";
        String baseUrl = "https://www.saucedemo.com/";
        String inventoryUrl = "https://www.saucedemo.com/inventory.html";

        getDriver().get(baseUrl);
        int originalAmountOfCookies = getDriver().manage().getCookies().size();
        getDriver().manage().addCookie(new Cookie("session-username", "standard_user"));
        int amountOfCookiesAfterAdding = getDriver().manage().getCookies().size();

        Assert.assertEquals(amountOfCookiesAfterAdding - originalAmountOfCookies, 1,
                "Number of cookies on page didn't increase by 1 after adding a cookie");

        getDriver().get(inventoryUrl);
        Cookie cookieNamed = getDriver().manage().getCookieNamed(cookieName);

        Assert.assertNotNull(cookieNamed,
                "Cookie '%s' is not available on the inventory page".formatted(cookieName));
        Assert.assertEquals(cookieNamed.getValue(), cookieValue,
                "Cookie '%s' has different value then expected".formatted(cookieName));
    }
}
