package ru.omickron.myspb.dao;

import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import ru.omickron.myspb.model.ReasonGroup;
import ru.omickron.myspb.model.User;

public interface ReasonGroupDao extends JpaRepository<ReasonGroup, Long> {
    @NonNull
    List<ReasonGroup> findByUser( @NonNull User user );

    @NonNull
    Optional<ReasonGroup> findByUserAndId( @NonNull User user, @NonNull Long id );

    @NonNull
    default ReasonGroup create( @NonNull User user, @NonNull String name, @Nullable ReasonGroup parent ) {
        ReasonGroup result = new ReasonGroup();
        result.setUser( user );
        result.setName( name );
        result.setParent( parent );
        return save( result );
    }
}
