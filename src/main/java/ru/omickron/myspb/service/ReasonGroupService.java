package ru.omickron.myspb.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.omickron.myspb.dao.ReasonGroupDao;
import ru.omickron.myspb.model.ReasonGroup;
import ru.omickron.myspb.model.User;

@Service
@AllArgsConstructor
public class ReasonGroupService {
    @NonNull
    private final ReasonGroupDao reasonGroupDao;

    @NonNull
    public List<ReasonGroup> findByUser( @NonNull User user ) {
        return reasonGroupDao.findByUser( user );
    }
}
