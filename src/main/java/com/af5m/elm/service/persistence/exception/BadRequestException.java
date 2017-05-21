package com.af5m.elm.service.persistence.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dell.isg.smi.commons.elm.exception.RuntimeCoreException;

/**
 * The Class BadRequestException.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeCoreException {
    private static final long serialVersionUID = 1L;
}
