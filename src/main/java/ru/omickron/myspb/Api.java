package ru.omickron.myspb;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Api {
    public static final String BASE = "https://gorod.gov.spb.ru/api/v3.2";
    public static final String TOKEN = BASE + "/token/";
    public static final String PROFILE = BASE + "/accounts/profile";
}
