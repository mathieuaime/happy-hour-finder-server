package com.mathieuaime.happyhourfinder.common.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "An unexpected error happened")
public class InternalServerErrorException extends RuntimeException {

}
