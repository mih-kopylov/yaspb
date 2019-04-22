package ru.omickron.myspb.service.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ReasonResponse {
    @NonNull
    private Long id;
    @NonNull
    private String name;
}
