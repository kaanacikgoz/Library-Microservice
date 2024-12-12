package com.acikgozkaan.book_service.entity;

public enum Category {
    HORROR, MYSTERY,  FANTASY, ROMANCE, DRAMA, THRILLER, FICTION, ADVENTURE, HISTORY, BIOGRAPHY;

    public static Category fromString(String name) {
        for (Category category : Category.values()) {
            if (category.name().equalsIgnoreCase(name)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown category: " + name);
    }

}
