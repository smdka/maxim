package ru.golovachev.riderservice.service;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.golovachev.riderservice.dto.RiderDto;
import ru.golovachev.riderservice.model.Rider;

@Mapper
public interface RiderMapper {
    RiderMapper INSTANCE = Mappers.getMapper(RiderMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rating", ignore = true)
    Rider toModel(RiderDto riderDto);

    @InheritInverseConfiguration(name = "toModel")
    RiderDto toDto(Rider rider);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rating", ignore = true)
    void updateFromDto(RiderDto riderDto, @MappingTarget Rider rider);
}
