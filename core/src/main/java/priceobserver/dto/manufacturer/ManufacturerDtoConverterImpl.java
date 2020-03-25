package priceobserver.dto.manufacturer;

import priceobserver.data.manufacturer.Manufacturer;

import static org.springframework.beans.BeanUtils.copyProperties;

public class ManufacturerDtoConverterImpl implements ManufacturerDtoConverter {
    @Override
    public ManufacturerDto convertToDto(Manufacturer entity) {
        ManufacturerDto dto = new ManufacturerDto();
        copyProperties(entity, dto);
        return dto;
    }

    @Override
    public Manufacturer convertToEntity(ManufacturerDto dto) {
        Manufacturer entity = new Manufacturer();
        copyProperties(dto, entity);
        return entity;
    }
}
