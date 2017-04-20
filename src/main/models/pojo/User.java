package main.models.pojo;

/**
 * Created by Di on 20.04.2017.
 */
public class User
{


    private int id;
    private String login;
    private String password;
    private boolean isBlocked;

    public User() {
    }

    public User(int id, String login, String password, boolean isBlocked) {

        this.id = id;
        this.login = login;
        this.password = password;
        this.isBlocked = isBlocked;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
