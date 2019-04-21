package ru.omickron.myspb.dao;

import java.util.List;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.omickron.myspb.model.ReasonGroup;
import ru.omickron.myspb.model.User;

public interface ReasonGroupDao extends JpaRepository<ReasonGroup, Long> {
    @NonNull
    List<ReasonGroup> findByUser( @NonNull User user );
}
