package ru.omickron.myspb.interceptor;

import java.util.Optional;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import ru.omickron.myspb.model.User;
import ru.omickron.myspb.service.dto.Token;

@Component
@RequestScope
@Setter
public class RequestContext {
    @Nullable
    private Token token;
    @Nullable
    private User user;

    @NonNull
    public Optional<Token> getToken() {
        return Optional.ofNullable( token );
    }

    @NonNull
    public Optional<User> getUser() {
        return Optional.ofNullable( user );
    }
}
