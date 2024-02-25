package util;

import java.util.HashMap;
import java.util.Map;

public class UserSession {

    private static UserSession userSession;
    private String username;
    private String password;

    private Map<String, String> filters = new HashMap<>();

    public UserSession(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static UserSession getInstance(String username, String password) {
        if (userSession == null) {
            userSession = new UserSession(username, password);
        }
        return userSession;
    }

    public static void addFilter(UserSession instance, String filterKey, String filterValue) {
        if (instance != null) {
            instance.getFilters().put(filterKey, filterValue);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static UserSession getUserSession() {
        return userSession;
    }

    public static void setUserSession(UserSession userSession) {
        UserSession.userSession = userSession;
    }

    public Map<String, String> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, String> filters) {
        this.filters = filters;
    }
}
