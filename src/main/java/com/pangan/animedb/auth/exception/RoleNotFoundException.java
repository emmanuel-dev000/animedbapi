package com.pangan.animedb.auth.exception;

import com.pangan.animedb.exceptionhandler.ApiException;

public class RoleNotFoundException extends ApiException {

    public static final String TITLE = "Role not found.";
    public static final String ERROR_MESSAGE = " was not found. ヽ(*。>Д<)o゜";

    public RoleNotFoundException(String role) {
        super(TITLE, role + ERROR_MESSAGE);
    }
}
