package ru.omickron.myspb.exception;

public class NoBodyInReasonGroupException extends AbstractFormattedException {
    public NoBodyInReasonGroupException() {
        super( "Reason group should have body" );
    }
}
