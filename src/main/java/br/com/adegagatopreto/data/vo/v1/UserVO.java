package br.com.adegagatopreto.data.vo.v1;

import br.com.adegagatopreto.enums.UserType;

import java.util.Objects;

public abstract class UserVO {
    private String email;
    private String password;
    private UserType type;

    public UserVO() {
    }

    public UserVO(String email, String password, UserType type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserVO userVO)) return false;
        return Objects.equals(getEmail(), userVO.getEmail()) && Objects.equals(getPassword(), userVO.getPassword()) && getType() == userVO.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword(), getType());
    }
}
