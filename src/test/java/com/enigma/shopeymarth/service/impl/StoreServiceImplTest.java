package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.dto.request.StoreRequest;
import com.enigma.shopeymarth.dto.response.StoreResponse;
import com.enigma.shopeymarth.entity.Store;
import com.enigma.shopeymarth.repository.StoreRepository;
import com.enigma.shopeymarth.service.StoreService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StoreServiceImplTest {

    // mock storeRepository
    private final StoreRepository storeRepository = mock(StoreRepository.class);

    // create StoreService-nya Interface
    private final StoreService storeService = new StoreServiceImpl(storeRepository);

    @Test
    void itShouldReturnStoreWhenCreateNewStore() {
//        Store dummyStore = new Store();
//        dummyStore.setId("123");
//        dummyStore.setName("Jaya Selalu");

        StoreRequest dummyStoreReq = new StoreRequest();
        dummyStoreReq.setId("123");
        dummyStoreReq.setName("Jaya");

//        StoreResponse dummyStoreResp = new StoreResponse();
//        dummyStoreResp.setId("123");
//        dummyStoreResp.setStoreName("Jaya");

        // mock behavior repository -> save
//        when(storeRepository.save(any(Store.class))).thenReturn(dummyStore); // using entity

        // perform then create operation
//        Store createStore = storeService.create(dummyStore); // using entity
        StoreResponse createStore = storeService.create(dummyStoreReq); // using dto

        // verify repository
//        verify(storeRepository, times(1)).save(dummyStore); // using entity
        verify(storeRepository).save(any(Store.class)); // using dto

        // verify/assert
//        assertEquals(dummyStore.getId(), createStore.getId()); // using entity
        assertEquals(dummyStoreReq.getName(), createStore.getStoreName()); // using dto

    }

    @Test
    void itShouldGetAllDataStoreWhenCallingGetAll() {
        List<Store> dummyStore = new ArrayList<>();
        dummyStore.add(new Store("1", "123", "Berkah Selalu", "Ragunan", "081923762173"));
        dummyStore.add(new Store("2", "124", "Berkah Selalu 2", "Ragunan", "0812819632"));
        dummyStore.add(new Store("3", "125", "Berkah Selalu 3", "Ragunan", "081923762173"));

        when(storeRepository.findAll()).thenReturn(dummyStore);
        List<Store> retrieveStore = storeService.getAll();

        assertEquals(dummyStore.size(), retrieveStore.size());

        for (int i = 0; i < dummyStore.size(); i++) {
            assertEquals(dummyStore.get(i).getName()
                    , retrieveStore.get(i).getName());
        }
    }

    @Test
    void itShouldGetDataStoreOneWhenGetByIdStore() {
        String storeId = "1";
        Store store = new Store("1", "123", "Berkah Selalu", "Ragunan", "081028015");
        when(storeRepository.findById(storeId)).thenReturn(Optional.of(store));

        StoreResponse storeResponse = storeService.getStoreById(storeId);

        verify(storeRepository).findById(storeId);
        assertNotNull(storeResponse);
        assertEquals(storeId, storeResponse.getId());
        assertEquals("Berkah Selalu", storeResponse.getStoreName());
    }

    @Test
    void itShouldReturnUpdatedStoreWhenCallingUpdate() {
//        Store storeReq = new Store("1", "123", "Berkah Selalu", "Ragunan", "081028015");
        StoreRequest storeReq = new StoreRequest();
        storeReq.setId("1");
        storeReq.setNoSiup("123");
        storeReq.setName("Berkah Selalu");
        storeReq.setAddress("Ragunan");
        storeReq.setMobilePhone("081028015");

        Store existingStore = new Store("1", "1234", "Berkah Jaya", "Ragunan", "081028015");


        when(storeRepository.findById(storeReq.getId())).thenReturn(Optional.of(existingStore));

        StoreResponse updateStore = storeService.updateStore(storeReq);

        verify(storeRepository, times(1)).findById(storeReq.getId());
        verify(storeRepository, times(1)).save(existingStore);

        assertNotNull(updateStore);
        assertEquals(storeReq.getId(), updateStore.getId());
        assertEquals(storeReq.getName(), updateStore.getStoreName());

    }

    @Test
    void itShouldCallDeleteOnceWhenCallingDelete() {
        String id = "1";
        storeService.delete(id);
        verify(storeRepository, times(1)).deleteById(id);
    }

}