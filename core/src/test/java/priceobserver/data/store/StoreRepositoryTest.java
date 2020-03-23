package priceobserver.data.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StoreRepositoryTest {

    private static final String STORE_NAME_1 = "Store_1";
    private static final String STORE_NAME_2 = "Store_2";
    private static final String STORE_NAME_3 = "Store_3";

    @Autowired
    private StoreRepository storeRepository;

    @Test
    public void shouldSaveStoreToDb() {
        Store store1 = new Store();
        store1.setName(STORE_NAME_1);

        Store store2 = new Store();
        store2.setName(STORE_NAME_2);

        Store store3 = new Store();
        store3.setName(STORE_NAME_3);

        storeRepository.saveAll(Arrays.asList(store1, store2, store3));

        assertEquals(3, storeRepository.count());

        List<Store> stores = (List<Store>) storeRepository.findAll();
        assertTrue(stores.stream().anyMatch(s -> s.getName().equals(STORE_NAME_1)));
        assertTrue(stores.stream().anyMatch(s -> s.getName().equals(STORE_NAME_2)));
        assertTrue(stores.stream().anyMatch(s -> s.getName().equals(STORE_NAME_3)));
    }
}