package ru.mihkopylov.myspb.aspect;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import ru.mihkopylov.myspb.interceptor.RequestContext;
import ru.mihkopylov.myspb.service.LoginService;
import ru.mihkopylov.myspb.service.dto.Token;

import static java.util.Objects.nonNull;

/**
 * If request to OurSpb is failed because of access token expired,
 * this aspect will try to refresh the token and repeat the original request.
 */
@Aspect
@Component
@AllArgsConstructor
public class RefreshTokenIfRequiredAspect {
    @NonNull
    private final RequestContext requestContext;
    @NonNull
    private final LoginService loginService;

    @Around("@annotation(ru.mihkopylov.myspb.aspect.RefreshTokenIfRequired)")
    public Object around( ProceedingJoinPoint joinPoint ) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (HttpClientErrorException.Unauthorized e) {
            if (nonNull( requestContext.getToken() )) {
                Token newToken = Token.fromResponse( loginService.refreshToken() );
                requestContext.setToken( newToken );
                return joinPoint.proceed();
            } else {
                throw e;
            }
        }
    }
}
