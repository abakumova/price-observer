package priceobserver.data.store;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class StoreRepositoryTest {

    private static final String STORE_NAME_1 = "Store_1";
    private static final String STORE_NAME_2 = "Store_2";
    private static final String STORE_NAME_3 = "Store_3";

    private static final String STORE_URL_1 = "http://localhost:8081/";
    private static final String STORE_URL_2 = "http://localhost:8082/";
    private static final String STORE_URL_3 = "http://localhost:8083/";

    private static Store store1;
    private static Store store2;
    private static Store store3;

    @Autowired
    private StoreRepository storeRepository;

    @BeforeAll
    static void setUp() {
        store1 = StoreBuilder.aStore().withName(STORE_NAME_1).withUrl(STORE_URL_1).build();
        store2 = StoreBuilder.aStore().withName(STORE_NAME_2).withUrl(STORE_URL_2).build();
        store3 = StoreBuilder.aStore().withName(STORE_NAME_3).withUrl(STORE_URL_3).build();
    }

    @Test
    public void shouldSaveStoresToDb() {
        storeRepository.saveAll(Arrays.asList(store1, store2, store3));

        assertEquals(3, storeRepository.count());

        List<Store> stores = (List<Store>) storeRepository.findAll();
        assertTrue(stores.stream().anyMatch(s -> s.getName().equals(STORE_NAME_1)));
        assertTrue(stores.stream().anyMatch(s -> s.getName().equals(STORE_NAME_2)));
        assertTrue(stores.stream().anyMatch(s -> s.getName().equals(STORE_NAME_3)));
    }

    @Test
    public void shouldFindStoreByPassedName() {
        storeRepository.saveAll(Arrays.asList(store1, store2, store3));

        assertTrue(storeRepository.findFirstByName(STORE_NAME_1).isPresent());
        assertTrue(storeRepository.findFirstByName(STORE_NAME_2).isPresent());
        assertTrue(storeRepository.findFirstByName(STORE_NAME_3).isPresent());
    }

    @Test
    public void shouldDeleteStoreFromDb() {
        storeRepository.saveAll(Arrays.asList(store1, store2, store3));
        Optional<Store> store = storeRepository.findFirstByName(store1.getName());

        assertTrue(store.isPresent());
        store.ifPresent(value -> storeRepository.delete(value));
        assertFalse(storeRepository.findFirstByName(STORE_NAME_1).isPresent());
    }
}