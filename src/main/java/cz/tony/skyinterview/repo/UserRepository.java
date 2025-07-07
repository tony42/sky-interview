package cz.tony.skyinterview.repo;

import cz.tony.skyinterview.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Antonin.Karasek
 * @since 2025-07-03
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
