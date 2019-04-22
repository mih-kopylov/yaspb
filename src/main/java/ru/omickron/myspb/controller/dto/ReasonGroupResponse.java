package ru.omickron.myspb.controller.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.lang.Nullable;
import ru.omickron.myspb.model.ReasonGroup;

import static java.util.Objects.isNull;

@Getter
@Setter
public class ReasonGroupResponse {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @Nullable
    private ReasonGroupResponse parent;
    @Nullable
    private Long reasonId;

    public ReasonGroupResponse( @NonNull ReasonGroup reasonGroup ) {
        id = reasonGroup.getId();
        name = reasonGroup.getName();
        parent = isNull( reasonGroup.getParent() ) ? null : new ReasonGroupResponse( reasonGroup.getParent() );
        reasonId = reasonGroup.getReasonId();
    }
}
