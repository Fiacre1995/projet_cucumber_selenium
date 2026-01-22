package api;

import utils.ApiClient;

public class UserApi {

    public static String deleteUser(String userId) {

        ApiClient.request()
                .delete("/users/" + userId)
                .then()
                .statusCode(204);

        return userId;
    }
}
