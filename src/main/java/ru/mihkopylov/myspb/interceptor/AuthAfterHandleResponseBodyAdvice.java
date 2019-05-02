package ru.mihkopylov.myspb.interceptor;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.mihkopylov.myspb.Const;

@ControllerAdvice
@AllArgsConstructor
public class AuthAfterHandleResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @NonNull
    private final RequestContext requestContext;

    @Override
    public boolean supports( MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType ) {
        return true;
    }

    @Override
    public Object beforeBodyWrite( Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response ) {
        requestContext.getToken().ifPresent( value -> response.getHeaders().add( Const.TOKEN, value.toString() ) );
        return body;
    }
}
