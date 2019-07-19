package ru.mihkopylov.myspb.service;

import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import ru.mihkopylov.myspb.Api;
import ru.mihkopylov.myspb.exception.NoBodyInReasonGroupException;
import ru.mihkopylov.myspb.exception.NoReasonInReasonGroupException;
import ru.mihkopylov.myspb.exception.PositionTypeNotFoundException;
import ru.mihkopylov.myspb.exception.ReasonGroupNotFoundException;
import ru.mihkopylov.myspb.exception.UserNotFoundException;
import ru.mihkopylov.myspb.interceptor.RequestContext;
import ru.mihkopylov.myspb.model.ReasonGroup;
import ru.mihkopylov.myspb.model.User;
import ru.mihkopylov.myspb.service.dto.BuildingResponse;
import ru.mihkopylov.myspb.service.dto.CategoryResponse;
import ru.mihkopylov.myspb.service.dto.CityObjectResponse;
import ru.mihkopylov.myspb.service.dto.CreatedProblemResponse;
import ru.mihkopylov.myspb.service.dto.PositionType;
import ru.mihkopylov.myspb.service.dto.ProblemResponse;
import ru.mihkopylov.myspb.service.dto.ProblemsPageResponse;
import ru.mihkopylov.myspb.service.dto.ReasonResponse;
import ru.mihkopylov.myspb.service.dto.ShortProblemResponse;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class ProblemsService {
    @NonNull
    private final ReasonGroupService reasonGroupService;
    @NonNull
    private final ReasonService reasonService;
    @NonNull
    private final HttpService httpService;
    @NonNull
    private final RequestContext requestContext;
    @NonNull
    private MapsService mapsService;

    @NonNull
    public List<ShortProblemResponse> getProblems() {
        return httpService.get( Api.MY_PROBLEMS, ProblemsPageResponse.class ).getResults();
    }

    @NonNull
    public ProblemResponse getProblem( @NonNull Long id ) {
        return httpService.get( Api.PROBLEMS + id, ProblemResponse.class );
    }

    @NonNull
    public ProblemResponse createProblem( @NonNull Long reasonGroupId, @NonNull Double latitude,
            @NonNull Double longitude, @NonNull MultipartFile[] files ) {
        User user = requestContext.getUser().orElseThrow( UserNotFoundException :: new );
        ReasonGroup reasonGroup = reasonGroupService.findByUserAndId( user, reasonGroupId )
                .orElseThrow( ReasonGroupNotFoundException :: new );
        if (isNull( reasonGroup.getBody() )) {
            throw new NoBodyInReasonGroupException();
        }
        if (isNull( reasonGroup.getReasonId() )) {
            throw new NoReasonInReasonGroupException();
        }
        PositionType positionType = reasonService.getReasons()
                .stream()
                .map( CityObjectResponse :: getCategories )
                .flatMap( Collection :: stream )
                .map( CategoryResponse :: getReasons )
                .flatMap( Collection :: stream )
                .filter( o -> o.getId().equals( reasonGroup.getReasonId() ) )
                .findAny()
                .map( ReasonResponse :: getPositionType )
                .orElseThrow( NoReasonInReasonGroupException :: new );

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add( "body", reasonGroup.getBody() );
        body.add( "reason", reasonGroup.getReasonId() );
        switch (positionType) {
            case BUILDING:
                BuildingResponse nearestBuilding = mapsService.getNearestBuilding( latitude, longitude );
                body.add( "latitude", nearestBuilding.getLatitude() );
                body.add( "longitude", nearestBuilding.getLongitude() );
                body.add( "building", nearestBuilding.getId() );
                break;
            case STREET:
                body.add( "latitude", latitude );
                body.add( "longitude", longitude );
                break;
            case NEAR_BUILDING:
                body.add( "latitude", latitude );
                body.add( "longitude", longitude );
                body.add( "nearest_building", mapsService.getNearestBuilding( latitude, longitude ).getId() );
                break;
            default:
                throw new PositionTypeNotFoundException();
        }
        for (MultipartFile file : files) {
            body.add( "files", file.getResource() );
        }
        CreatedProblemResponse response = httpService.post( Api.PROBLEMS, body, CreatedProblemResponse.class );
        return getProblem( response.getId() );
    }
}
