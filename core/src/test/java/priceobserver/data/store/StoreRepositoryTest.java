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

    private static Store store1;
    private static Store store2;
    private static Store store3;

    @Autowired
    private StoreRepository storeRepository;

    @BeforeAll
    static void setUp() throws Exception {
        store1 = new Store();
        store1.setName(STORE_NAME_1);

        store2 = new Store();
        store2.setName(STORE_NAME_2);

        store3 = new Store();
        store3.setName(STORE_NAME_3);
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