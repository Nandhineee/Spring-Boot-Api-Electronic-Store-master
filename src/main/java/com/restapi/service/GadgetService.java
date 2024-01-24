package com.restapi.service;


import com.restapi.dto.GadgetDto;
import com.restapi.exception.common.ResourceNotFoundException;
//import com.restapi.model.Category;
import com.restapi.model.Gadget;
//import com.restapi.repository.CategoryRepository;
import com.restapi.repository.GadgetRepository;
import com.restapi.request.GadgetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class GadgetService {

    @Autowired
    private GadgetDto gadgetDto;

//    @Autowired
//    private CategoryRepository categoryRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private GadgetRepository gadgetRepository ;

    public List<Gadget> findAll() {
        return gadgetRepository.findAll();
    }

    @Transactional
    public List<Gadget> createBook(GadgetRequest gadgetRequest) {
        Gadget gadget = gadgetDto.mapToGadget(gadgetRequest);
//        Category category = categoryRepository.findById(gadgetRequest.getCategoryId())
//                .orElseThrow(() -> new ResourceNotFoundException("CategoryId",
//                        "CategoryId", gadgetRequest.getCategoryId()));
//        gadget.setCategory(category);
        System.out.println("Stock Checking"+gadget.getStock());
        gadgetRepository.save(gadget);
        return findAll();
    }

    @Transactional
    public List<Gadget> updateBook(GadgetRequest gadgetRequest) {
        Gadget gadget = gadgetDto.mapToGadget(gadgetRequest);
//        Category category = categoryRepository.findById(gadgetRequest.getCategoryId())
//                .orElseThrow(() -> new ResourceNotFoundException("CategoryId",
//                        "CategoryId", gadgetRequest.getCategoryId()));
//        gadget.setCategory(category);
        gadgetRepository.save(gadget);
        return findAll();
    }

    public List<Gadget> deleteById(Integer id) {
        gadgetRepository.deleteById(Long.valueOf(id));
        return findAll();
    }

    public File getFile(Long id) throws IOException {
        Gadget gadget= gadgetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "id", id));
        Resource resource = storageService.loadFileAsResource(gadget.getPhoto());
        return resource.getFile();
    }
}
