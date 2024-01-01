package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.dto.request.StoreRequest;
import com.enigma.shopeymarth.dto.response.StoreResponse;
import com.enigma.shopeymarth.entity.Store;
import com.enigma.shopeymarth.repository.StoreRepository;
import com.enigma.shopeymarth.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public Store create(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store getById(String id) {
        return storeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Store> getAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store update(Store store) {
        Store currentStoreId = storeRepository.findById(store.getId()).orElse(null);
        if (currentStoreId != null) {
            store.setName(store.getName());
            store.setNoSiup(store.getNoSiup());
            store.setAddress(store.getAddress());
            store.setMobilePhone(store.getMobilePhone());
            return storeRepository.save(store);
        }
        return null;
    }

    @Override
    public void delete(String id) {
        storeRepository.deleteById(id);
    }

    @Override
    public StoreResponse create(StoreRequest storeRequest) {
        Store store = Store.builder()
                .name(storeRequest.getName())
                .noSiup(storeRequest.getNoSiup())
                .address(storeRequest.getAddress())
                .mobilePhone(storeRequest.getMobilePhone())
                .build();
        storeRepository.save(store);
        return StoreResponse.builder()
                .storeName(store.getName())
                .noSiup(storeRequest.getNoSiup())
                .address(storeRequest.getAddress())
                .phone(storeRequest.getMobilePhone())
                .build();
    }

    @Override
    public StoreResponse getStoreById(String id) {
        Store store = storeRepository.findById(id).orElse(null);
        if (store != null) {
            return StoreResponse.builder()
                    .id(store.getId())
                    .storeName(store.getName())
                    .noSiup(store.getNoSiup())
                    .build();
        }
        return null;
    }

    @Override
    public StoreResponse updateStore(StoreRequest storeRequest) {
        Store store = storeRepository.findById(storeRequest.getId()).orElse(null);
        if (store != null) {
            store.setName(storeRequest.getName());
            store.setNoSiup(storeRequest.getNoSiup());
            store.setAddress(storeRequest.getAddress());
            store.setMobilePhone(storeRequest.getMobilePhone());
            storeRepository.save(store);
            return StoreResponse.builder()
                    .id(store.getId())
                    .noSiup(store.getNoSiup())
                    .storeName(store.getName())
                    .address(store.getAddress())
                    .phone(store.getMobilePhone())
                    .build();
        } return null;
    }
}
