package priceobserver.dto.productproperties;

import org.springframework.stereotype.Component;
import priceobserver.data.productproperties.ProductProperties;

import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public class ProductPropertiesDtoConverterImpl implements ProductPropertiesDtoConverter {
    @Override
    public ProductPropertiesDto convertToDto(ProductProperties entity) {
        ProductPropertiesDto dto = new ProductPropertiesDto();
        copyProperties(entity, dto);
        return dto;
    }

    @Override
    public ProductProperties convertToEntity(ProductPropertiesDto dto) {
        ProductProperties entity = new ProductProperties();
        copyProperties(dto, entity);
        return entity;
    }
}
