package com.saad.library_management_system.error;

import java.time.LocalDateTime;

public record ErrorResponse(int statusCode, String message, String description, LocalDateTime timestamp) {
}
