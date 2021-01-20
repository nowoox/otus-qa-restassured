import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class User {

    public static String generateString() {
        return  RandomStringUtils.randomAlphabetic(10).toLowerCase();
    }

    public static String generateName() {
        return "name-" + generateString();
    }

    public static String generateUser(String userName) throws JSONException {

        JSONObject jsonObject = new JSONObject()
                .put("id", "0")
                .put("username", userName)
                .put("firstName", generateString())
                .put("lastName", generateString())
                .put("email", generateString() + "@ma.il")
                .put("password", generateString())
                .put("phone", "1")
                .put("userStatus", "1");

        return jsonObject.toString();
    }
}
