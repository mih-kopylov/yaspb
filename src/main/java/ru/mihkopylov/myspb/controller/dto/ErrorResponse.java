package ru.mihkopylov.myspb.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    @NonNull
    private final String message;
}
