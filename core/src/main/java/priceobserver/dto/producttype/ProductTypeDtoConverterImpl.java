package priceobserver.dto.producttype;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import priceobserver.data.producttype.ProductType;

import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public class ProductTypeDtoConverterImpl implements ProductTypeDtoConverter {
    @Override
    public ProductTypeDto convertToDto(ProductType entity) {
        ProductTypeDto dto = new ProductTypeDto();
        copyProperties(entity, dto);
        return dto;
    }

    @Override
    public ProductType convertToEntity(ProductTypeDto dto) {
        ProductType entity = new ProductType();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
