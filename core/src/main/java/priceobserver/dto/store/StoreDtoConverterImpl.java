package priceobserver.dto.store;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import priceobserver.data.store.Store;

@Component
public class StoreDtoConverterImpl implements StoreDtoConverter {
    @Override
    public StoreDto convertToDto(Store entity) {
        StoreDto storeDto = new StoreDto();
        BeanUtils.copyProperties(entity, storeDto);
        return storeDto;
    }

    @Override
    public Store convertToEntity(StoreDto dto) {
        Store storeEntity = new Store();
        BeanUtils.copyProperties(dto, storeEntity);
        return storeEntity;
    }
}
