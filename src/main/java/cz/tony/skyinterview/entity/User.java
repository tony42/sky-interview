package cz.tony.skyinterview.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * @author Antonin.Karasek
 * @since 2025-07-03
 */
@Entity
public class User {

    @Id
    String email;
    String name;

    public User() {
    }

    public User(final String email, final String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name);
    }

}
