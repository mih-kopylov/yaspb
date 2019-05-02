package ru.mihkopylov.myspb.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.mihkopylov.myspb.controller.dto.TokenHeader;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Component
public class HeaderTokenConverter implements Converter<String, TokenHeader> {
    private static final String SEPARATOR = ":";

    @Override
    public TokenHeader convert( String value ) {
        if (isBlank( value )) {
            return null;
        }
        String[] parts = value.split( SEPARATOR );
        if (parts.length != 2) {
            throw new IllegalArgumentException( "Can't parse tokens from header" );
        }
        return new TokenHeader( parts[0], parts[1] );
    }
}
