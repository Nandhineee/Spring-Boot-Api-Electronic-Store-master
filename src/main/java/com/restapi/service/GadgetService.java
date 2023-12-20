package com.restapi.service;


import com.restapi.dto.GadgetDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Category;
import com.restapi.model.Gadget;
import com.restapi.repository.CategoryRepository;
import com.restapi.repository.GadgetRepository;
import com.restapi.request.GadgetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GadgetService {

    @Autowired
    private GadgetDto gadgetDto;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GadgetRepository gadgetRepository ;

    public List<Gadget> findAll() {
        return gadgetRepository.findAll();
    }

    @Transactional
    public List<Gadget> createBook(GadgetRequest gadgetRequest) {
        Gadget gadget = gadgetDto.mapToBook(gadgetRequest);
        Category category = categoryRepository.findById(gadgetRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("CategoryId",
                        "CategoryId", gadgetRequest.getCategoryId()));
        gadget.setCategory(category);
        gadgetRepository.save(gadget);
        return findAll();
    }

    @Transactional
    public List<Gadget> updateBook(GadgetRequest gadgetRequest) {
        Gadget gadget = gadgetDto.mapToBook(gadgetRequest);
        Category category = categoryRepository.findById(gadgetRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("CategoryId",
                        "CategoryId", gadgetRequest.getCategoryId()));
        gadget.setCategory(category);
        gadgetRepository.save(gadget);
        return findAll();
    }

    public List<Gadget> deleteById(Integer id) {
        gadgetRepository.deleteById(Long.valueOf(id));
        return findAll();
    }
}
