package byk.app.jwt;

import javax.validation.constraints.NotNull;

public class RequestUser {

    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String role;

    public RequestUser() {

    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return username;
    }
}