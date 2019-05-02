package ru.mihkopylov.myspb.interceptor;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.mihkopylov.myspb.Const;
import ru.mihkopylov.myspb.controller.dto.TokenHeader;
import ru.mihkopylov.myspb.model.User;
import ru.mihkopylov.myspb.service.UserService;
import ru.mihkopylov.myspb.service.dto.Token;
import ru.mihkopylov.myspb.controller.converter.HeaderTokenConverter;

import static java.util.Objects.nonNull;

/**
 * This interceptor gets
 */
@Component
@AllArgsConstructor
public class AuthBeforeHandleInterceptor implements HandlerInterceptor {
    @NonNull
    private final HeaderTokenConverter headerTokenConverter;
    @NonNull
    private final RequestContext requestContext;
    @NonNull
    private final UserService userService;

    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) {
        String tokenHeaderString = request.getHeader( Const.TOKEN );
        if (nonNull( tokenHeaderString ) && !tokenHeaderString.isBlank()) {
            TokenHeader tokenHeader = headerTokenConverter.convert( tokenHeaderString );
            Token token = Optional.ofNullable( tokenHeader ).map( TokenHeader :: toToken ).orElse( null );
            if (nonNull( token )) {
                Optional<User> user = userService.findUserByToken( token.getAccessToken() );
                if (user.isPresent()) {
                    requestContext.setToken( token );
                    requestContext.setUser( user.get() );
                }
            }
        }
        return true;
    }
}
