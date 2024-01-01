package com.enigma.shopeymarth.controller;

import com.enigma.shopeymarth.constant.AppPath;
import com.enigma.shopeymarth.dto.request.StoreRequest;
import com.enigma.shopeymarth.dto.response.StoreResponse;
import com.enigma.shopeymarth.entity.Store;
import com.enigma.shopeymarth.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.STORE)
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public Store createStore(@RequestBody Store store) {
        return storeService.create(store);
    }

    @GetMapping(value = "/{id}")
    public Store getStoreById(@PathVariable String id) {
        return storeService.getById(id);
    }

    @GetMapping
    public List<Store> getAll() {
        return storeService.getAll();
    }

    @PutMapping
    public Store update(@RequestBody Store store) {
        Store currentStoreId = getStoreById(store.getId());
        if (currentStoreId != null) return storeService.update(store);
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) {
        storeService.delete(id);
    }

    @PostMapping("/v1")
    public StoreResponse createStores(@RequestBody StoreRequest storeRequest) {
        return storeService.create(storeRequest);
    }
}
