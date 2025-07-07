package cz.tony.skyinterview.dto;

/**
 * @author Antonin.Karasek
 * @since 2025-07-07
 */
public class UserDto {

    private String email;
    private String name;

    public UserDto(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

}
