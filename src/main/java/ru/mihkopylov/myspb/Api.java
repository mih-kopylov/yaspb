package ru.mihkopylov.myspb;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Api {
    private static final String BASE = "https://gorod.gov.spb.ru";
    private static final String API = BASE + "/api/v3.2";
    public static final String TOKEN = API + "/token/";
    public static final String PROFILE = API + "/accounts/profile";
    public static final String CLASSIFIER = API + "/classifier";
    public static final String MY_PROBLEMS = API + "/problems/my/";
    public static final String PROBLEMS = API + "/problems/";
    private static final String PUBLIC_API = BASE + "/public_api";
    public static final String NEAREST = PUBLIC_API + "/maps/get_nearest";
}
