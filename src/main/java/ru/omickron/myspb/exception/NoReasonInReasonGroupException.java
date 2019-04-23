package ru.omickron.myspb.exception;

public class NoReasonInReasonGroupException extends AbstractFormattedException {
    public NoReasonInReasonGroupException() {
        super( "Reason group should have reason" );
    }
}
