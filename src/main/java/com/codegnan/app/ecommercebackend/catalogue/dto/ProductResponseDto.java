package com.codegnan.app.ecommercebackend.catalogue.dto;

import java.time.LocalDateTime;

public record ProductResponseDto(int id, String name, String brand, String description, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
}