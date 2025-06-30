package com.github.solisa14.fitlogbackend.dto;

public class EnumDto {
    private final String name;
    private final String displayName;

    public EnumDto(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }
}
