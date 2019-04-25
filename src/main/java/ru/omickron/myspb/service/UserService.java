package ru.omickron.myspb.service;

import java.net.URI;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.omickron.myspb.Api;
import ru.omickron.myspb.dao.UserDao;
import ru.omickron.myspb.model.User;
import ru.omickron.myspb.service.dto.ProfileResponse;
import ru.omickron.myspb.service.dto.Token;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    @NonNull
    private final UserDao userDao;

    @NonNull
    public ProfileResponse getProfile( @NonNull Token token ) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth( token.getAccessToken() );
        RequestEntity<String> requestEntity = new RequestEntity<>( headers, HttpMethod.GET, URI.create( Api.PROFILE ) );
        return restTemplate.exchange( requestEntity, ProfileResponse.class ).getBody();
    }

    public void updateUser( @NonNull String login, @NonNull String accessToken ) {
        Optional<User> user = userDao.findByLogin( login );
        if (user.isEmpty()) {
            userDao.create( login );
        } else {
            User savedUser = user.get();
            savedUser.setToken( accessToken );
            userDao.save( savedUser );
        }
    }

    @NonNull
    public Optional<User> findUserByToken( @NonNull String token ) {
        return userDao.findByToken( token );
    }
}
