package dos;

import com.google.common.base.Objects;

import java.util.Date;

/**
 * user info
 *
 * @author suzl
 */
public class UserInfoDo {
    private Long id;

    private Integer age;

    private String name;

    private Date birthday;

    public UserInfoDo(Long id, Integer age, String name, Date birthday) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.birthday = birthday;
    }

    public UserInfoDo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfoDo that = (UserInfoDo) o;
        return Objects.equal(id, that.id) && Objects.equal(age, that.age) && Objects.equal(name, that.name) && Objects.equal(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, age, name, birthday);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
