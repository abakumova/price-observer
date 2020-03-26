package priceobserver.dto.store;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import priceobserver.data.store.Store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static priceobserver.dto.testutils.DtoTestPreparationHelper.getPreparedStoreDto;
import static priceobserver.dto.testutils.DtoTestPreparationHelper.getPreparedStoreEntity;

@SpringBootTest
class StoreDtoConverterImplTest {

    private static StoreDto storeDto;
    private static Store storeEntity;

    @Autowired
    private StoreDtoConverter storeDtoConverter;

    @BeforeAll
    static void setUp() {
        storeDto = getPreparedStoreDto();
        storeEntity = getPreparedStoreEntity();
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