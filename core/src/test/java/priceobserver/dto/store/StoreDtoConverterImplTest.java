package priceobserver.dto.store;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import priceobserver.data.store.Store;
import priceobserver.data.store.StoreBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StoreDtoConverterImplTest {

    private static final String STORE_NAME = "Store name";
    private static final Long STORE_ID = 1L;

    private static StoreDto storeDto;
    private static Store storeEntity;

    @Autowired
    private StoreDtoConverter storeDtoConverter;

    @BeforeAll
    static void setUp() {
        storeDto = StoreDtoBuilder.aStoreDto()
                .withId(STORE_ID)
                .withName(STORE_NAME)
                .build();

        storeEntity = StoreBuilder.aStore()
                .withId(STORE_ID)
                .withName(STORE_NAME)
                .build();
    }

    @Test
    void shouldConvertToDto() {
        StoreDto storeConvertedToDto = storeDtoConverter.convertToDto(storeEntity);

        assertEquals(storeDto, storeConvertedToDto);
    }

    @Test
    void shouldConvertToEntity() {
        Store storeDtoConvertedToEntity = storeDtoConverter.convertToEntity(storeDto);

        assertEquals(storeEntity, storeDtoConvertedToEntity);
    }
}