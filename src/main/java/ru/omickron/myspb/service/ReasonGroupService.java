package ru.omickron.myspb.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.omickron.myspb.dao.ReasonGroupDao;
import ru.omickron.myspb.model.ReasonGroup;
import ru.omickron.myspb.model.User;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class ReasonGroupService {
    @NonNull
    private final ReasonGroupDao reasonGroupDao;

    @NonNull
    public List<ReasonGroup> findByUser( @NonNull User user ) {
        return reasonGroupDao.findByUser( user );
    }

    @NonNull
    public ReasonGroup createGroup( @NonNull User user, @NonNull String name, @Nullable Long parentId ) {
        ReasonGroup parent = isNull( parentId ) ? null : reasonGroupDao.findById( parentId ).orElse( null );
        return reasonGroupDao.create( user, name, parent );
    }

    @NonNull
    public Optional<ReasonGroup> findByUserAndId( @NonNull User user, @NonNull Long id ) {
        return reasonGroupDao.findByUserAndId( user, id );
    }

    @NonNull
    public ReasonGroup updateReasonGroup( @NonNull ReasonGroup reasonGroup, @NonNull String name,
            @Nullable Long parentId ) {
        ReasonGroup parent = isNull( parentId ) ? null : reasonGroupDao.findById( parentId ).orElse( null );
        reasonGroup.setName( name );
        reasonGroup.setParent( parent );
        return reasonGroupDao.save( reasonGroup );
    }

    public void deleteReasonGroup( @NonNull ReasonGroup reasonGroup ) {
        reasonGroupDao.delete( reasonGroup );
    }
}
