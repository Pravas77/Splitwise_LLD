import java.util.Objects;

public class User {
    private int id;

    public User(int id) {
        this.id = id;
    }

    public int getid() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "User" + id;
    }
}
