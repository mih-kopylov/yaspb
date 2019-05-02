package ru.mihkopylov.myspb.dao;

import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.mihkopylov.myspb.model.User;

public interface UserDao extends JpaRepository<User, Long> {
    @NonNull
    Optional<User> findByLogin( @NonNull String login );

    @NonNull
    Optional<User> findByToken( @NonNull String token );

    default User create( @NonNull String login ) {
        User result = new User();
        result.setLogin( login );
        return save( result );
    }
}
