package ir.iraniancyber.khaneshyar.model;

import jakarta.persistence.*;

@Table(name = "app_users")
@Entity
public class User {
    private int id;
    private String mail;
    private int code;
    private boolean ok;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public User(int id, String mail, int code, boolean ok) {
        this.id = id;
        this.mail = mail;
        this.code = code;
        this.ok = ok;
    }

    public User() {
    }
}
