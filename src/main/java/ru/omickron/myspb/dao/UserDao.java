package ru.omickron.myspb.dao;

import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.omickron.myspb.model.User;

public interface UserDao extends JpaRepository<User, Long> {
    @NonNull Optional<User> findByLogin( @NonNull String login );

    @NonNull Optional<User> findByToken( @NonNull String token );

    default User create( @NonNull String login, @NonNull String userName ) {
        User result = new User();
        result.setLogin( login );
        result.setName( userName );
        return save( result );
    }
}
