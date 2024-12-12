package com.acikgozkaan.book_service.dto;

import com.acikgozkaan.book_service.entity.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateRequest {

    @NotBlank(message = "Title cannot be empty")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

    @NotBlank(message = "Author cannot be empty")
    @Size(max = 50, message = "Author must be less than 50 characters")
    private String author;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Min(0)
    private Integer stock;

}
