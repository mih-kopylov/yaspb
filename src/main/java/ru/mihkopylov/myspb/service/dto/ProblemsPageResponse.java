package ru.mihkopylov.myspb.service.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemsPageResponse {
    private Long count;
    private String next;
    private String previous;
    private List<ShortProblemResponse> results = new ArrayList<>();
}
