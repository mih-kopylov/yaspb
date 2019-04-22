package ru.omickron.myspb.exception;

public class ReasonNotFoundException extends AbstractFormattedException {
    public ReasonNotFoundException() {
        super( "Can't find reason" );
    }
}
