package priceobserver.dto.store;

import org.springframework.stereotype.Component;
import priceobserver.data.store.Store;
import priceobserver.data.store.StoreBuilder;

@Component
public class StoreDtoConverterImpl implements StoreDtoConverter {
    @Override
    public StoreDto convertToDto(Store entity) {
        return StoreDtoBuilder.aStoreDto()
                .withId(entity.getId())
                .withName(entity.getName())
                .build();
    }

    @Override
    public Store convertToEntity(StoreDto dto) {
        return StoreBuilder.aStore()
                .withId(dto.getId())
                .withName(dto.getName())
                .build();
    }
}
