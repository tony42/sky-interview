package cz.tony.skyinterview.entity;

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
}
