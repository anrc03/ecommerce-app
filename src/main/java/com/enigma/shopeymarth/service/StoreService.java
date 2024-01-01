package com.enigma.shopeymarth.service;

import com.enigma.shopeymarth.dto.request.StoreRequest;
import com.enigma.shopeymarth.dto.response.StoreResponse;
import com.enigma.shopeymarth.entity.Store;

import java.util.List;

public interface StoreService {
    Store create(Store store);
    Store getById(String id);
    List<Store> getAll();
    Store update(Store store);
    void delete(String id);

    StoreResponse create(StoreRequest storeRequest);
    StoreResponse getStoreById(String id);
    StoreResponse updateStore(StoreRequest request);
}
