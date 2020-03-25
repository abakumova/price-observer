package priceobserver.dto.store;

import org.springframework.stereotype.Component;
import priceobserver.data.store.Store;

import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public class StoreDtoConverterImpl implements StoreDtoConverter {
    @Override
    public StoreDto convertToDto(Store entity) {
        StoreDto storeDto = new StoreDto();
        copyProperties(entity, storeDto);
        return storeDto;
    }

    @Override
    public Store convertToEntity(StoreDto dto) {
        Store storeEntity = new Store();
        copyProperties(dto, storeEntity);
        return storeEntity;
    }
}
