package api.models.response;

public class LoginResponse {

    private String access_token;
    private String refresh_token;
    private boolean expired;

    public String getAccess_token() {
        return access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public boolean getExpired() {
        return expired;
    }
}
