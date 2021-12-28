package com.segunfrancis.payoneerpaymentmethods.util;

import androidx.annotation.NonNull;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.time.temporal.ValueRange;

import retrofit2.HttpException;

public class ErrorUtils {

    @NonNull
    public static String handleThrowable(Throwable t) {
        String error;
        ValueRange range = ValueRange.of(500, 599);
        if (t instanceof UnknownHostException) {
            error = "We've detected a network problem. Please check your internet connection and try again";
        } else if (t instanceof HttpException && range.isValidValue(((HttpException) t).code())) {
            error = "Sorry, we are currently experiencing some internal technical difficulties. Please give it a moment and try again";
        } else if (t instanceof SocketTimeoutException) {
            error = "Please check your connectivity and try again";
        } else {
            error = "Unknown error";
        }
        return error;
    }
}
