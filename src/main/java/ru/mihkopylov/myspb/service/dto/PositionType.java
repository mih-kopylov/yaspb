package ru.mihkopylov.myspb.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.mihkopylov.myspb.exception.PositionTypeNotFoundException;

@AllArgsConstructor
@Getter
public enum PositionType {
    BUILDING( 1 ),
    STREET( 2 ),
    NEAR_BUILDING( 4 );
    private final int id;

    @JsonCreator
    public static PositionType fromId( int id ) {
        return Arrays.stream( PositionType.values() )
                .filter( o -> o.getId() == id )
                .findAny()
                .orElseThrow( PositionTypeNotFoundException :: new );
    }
}
