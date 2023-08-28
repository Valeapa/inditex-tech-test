package com.valea.inditextechtest.infrastructure.price.controller.dto;

import java.time.LocalDateTime;

/**
 * Dto containing error info to attach to response's body in case an exception occurs.
 * @param error significant message describing the error
 * @param timestamp error timestamp
 */
public record ExceptionDto(String error, LocalDateTime timestamp) {
}
