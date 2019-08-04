package ru.mihkopylov.myspb.dao;

import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import ru.mihkopylov.myspb.model.ReasonGroup;
import ru.mihkopylov.myspb.model.User;

public interface ReasonGroupDao extends JpaRepository<ReasonGroup, Long> {
    @NonNull List<ReasonGroup> findByUser( @NonNull User user );

    void deleteByUser( @NonNull User user );

    @NonNull Optional<ReasonGroup> findByUserAndId( @NonNull User user, @NonNull Long id );

    @NonNull
    default ReasonGroup create( @NonNull User user, @NonNull String name, @Nullable ReasonGroup parent,
            @Nullable Long reasonId, @Nullable String body ) {
        ReasonGroup result = new ReasonGroup();
        result.setUser( user );
        result.setName( name );
        result.setParent( parent );
        result.setReasonId( reasonId );
        result.setBody( body );
        return save( result );
    }
}
