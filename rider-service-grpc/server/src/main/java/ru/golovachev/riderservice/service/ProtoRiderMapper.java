package ru.golovachev.riderservice.service;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.golovachev.riderservice.dto.RiderDto;
import ru.golovachev.riderservice.model.Rider;

@Mapper
public interface ProtoRiderMapper {
    ProtoRiderMapper INSTANCE = Mappers.getMapper(ProtoRiderMapper.class);

    @Mapping(target = "id", ignore = true)
    Rider toModel(RiderDto grpcRiderDto);

    @InheritInverseConfiguration(name = "toModel")
    RiderDto toDto(Rider rider);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(RiderDto grpcRiderDto, @MappingTarget Rider rider);
}
