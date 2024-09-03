package com.mathieuaime.hhf.geo;

public class CoordinatesMapper {
    public static CoordinatesDto toDto(Coordinates model) {
        CoordinatesDto dto = new CoordinatesDto();
        dto.setLatitude(model.latitude());
        dto.setLongitude(model.longitude());
        return dto;
    }

    public static Coordinates toModel(CoordinatesDto dto) {
        return new Coordinates(dto.getLatitude(), dto.getLongitude());
    }
}
