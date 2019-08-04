package ru.mihkopylov.myspb.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mihkopylov.myspb.dao.ReasonGroupDao;
import ru.mihkopylov.myspb.exception.ReasonGroupNotFoundException;
import ru.mihkopylov.myspb.exception.ReasonNotFoundException;
import ru.mihkopylov.myspb.model.ReasonGroup;
import ru.mihkopylov.myspb.model.User;
import ru.mihkopylov.myspb.service.dto.CategoryResponse;
import ru.mihkopylov.myspb.service.dto.CityObjectResponse;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
@Transactional
public class ReasonGroupService {
    @NonNull
    private final ReasonGroupDao reasonGroupDao;
    @NonNull
    private final ReasonService reasonService;

    @NonNull
    public List<ReasonGroup> findByUser( @NonNull User user ) {
        return reasonGroupDao.findByUser( user );
    }

    @NonNull
    public ReasonGroup createGroup( @NonNull User user, @NonNull String name, @Nullable Long parentId,
            @Nullable Long reasonId, @Nullable String body ) {
        validateReasonId( reasonId );
        ReasonGroup parent = isNull( parentId ) ? null
                : reasonGroupDao.findById( parentId ).orElseThrow( ReasonGroupNotFoundException :: new );
        return reasonGroupDao.create( user, name, parent, reasonId, body );
    }

    @NonNull
    public Optional<ReasonGroup> findByUserAndId( @NonNull User user, @NonNull Long id ) {
        return reasonGroupDao.findByUserAndId( user, id );
    }

    @NonNull
    public ReasonGroup updateReasonGroup( @NonNull ReasonGroup reasonGroup, @NonNull String name,
            @Nullable Long parentId, @Nullable Long reasonId, @Nullable String body ) {
        validateReasonId( reasonId );
        ReasonGroup parent = isNull( parentId ) ? null
                : reasonGroupDao.findById( parentId ).orElseThrow( ReasonGroupNotFoundException :: new );
        reasonGroup.setName( name );
        reasonGroup.setParent( parent );
        reasonGroup.setReasonId( reasonId );
        reasonGroup.setBody( body );
        return reasonGroupDao.save( reasonGroup );
    }

    public void deleteReasonGroup( @NonNull ReasonGroup reasonGroup ) {
        reasonGroupDao.delete( reasonGroup );
    }

    public void importFromUser( @NonNull User user, @NonNull User userToImport ) {
        reasonGroupDao.deleteByUser( user );
        Multimap<Long, ReasonGroup> groupsToImportByParent = ArrayListMultimap.create();
        reasonGroupDao.findByUser( userToImport )
                .forEach( o -> groupsToImportByParent.put( getReasonGroupParentId( o ).orElse( null ), o ) );
        importGroupsRecursively( user, groupsToImportByParent.get( null ), null, groupsToImportByParent );
    }

    @NonNull
    private Optional<Long> getReasonGroupParentId( @NonNull ReasonGroup reasonGroup ) {
        return Optional.of( reasonGroup ).map( ReasonGroup :: getParent ).map( ReasonGroup :: getId );
    }

    private void importGroupsRecursively( @NonNull User user, @NonNull Collection<ReasonGroup> groupsToImport,
            @Nullable Long parentGroupId, @NonNull Multimap<Long, ReasonGroup> groupsToImportByParent ) {
        for (ReasonGroup groupToImport : groupsToImport) {
            ReasonGroup group = createGroup( user, groupToImport.getName(), parentGroupId, groupToImport.getReasonId(),
                    groupToImport.getBody() );
            importGroupsRecursively( user, groupsToImportByParent.get( groupToImport.getId() ), group.getId(),
                    groupsToImportByParent );
        }
    }

    private void validateReasonId( @Nullable Long reasonId ) {
        if (nonNull( reasonId )) {
            reasonService.getReasons()
                    .stream()
                    .map( CityObjectResponse :: getCategories )
                    .flatMap( Collection :: stream )
                    .map( CategoryResponse :: getReasons )
                    .flatMap( Collection :: stream )
                    .filter( o -> o.getId().equals( reasonId ) )
                    .findAny()
                    .orElseThrow( ReasonNotFoundException :: new );
        }
    }
}
