package ru.mihkopylov.myspb.service;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mihkopylov.myspb.Api;
import ru.mihkopylov.myspb.dao.UserDao;
import ru.mihkopylov.myspb.model.User;
import ru.mihkopylov.myspb.service.dto.ProfileResponse;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    @NonNull
    private final UserDao userDao;
    @NonNull
    private final HttpService httpService;

    @NonNull
    public ProfileResponse getProfile() {
        return httpService.get( Api.PROFILE, ProfileResponse.class );
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
