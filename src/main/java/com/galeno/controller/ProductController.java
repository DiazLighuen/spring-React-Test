package com.galeno.controller;

import com.galeno.dto.ProductDTO;
import com.galeno.dto.ProductListingDTO;
import com.galeno.model.FilterCriteria;
import com.galeno.model.Product;
import com.galeno.service.ProductService;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("product")
@Getter
public class ProductController {

    @Autowired
    private ProductService service;
    private ModelMapper modelMapper;

    public ProductController(ModelMapper mapper){
        this.modelMapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> read(){
        return okResponse(getService().findAll());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ProductListingDTO>> read(
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(required = false) FilterCriteria filterCriteria,
        @RequestParam(required = false) String filterValue,
        @RequestParam(required = false) String[] with
    )
    {
        return okListingResponse(getService().findPage(page, pageSize,filterCriteria, filterValue, with));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> read(@PathVariable(name = "id") Long id){
        return okResponse(getServices().getById(id));
    }

    private ResponseEntity<List<ProductDTO>> okResponse(List<Product> src){
        return ResponseEntity.ok(src.stream().map(this::toDTO).collect(Collectors.toList()));
    }

    private ResponseEntity<Page<ProductListingDTO>> okListingResponse(Page<Product> src){
        return ResponseEntity.ok(src.map(this::toListingDTO));
    }

    private ProductDTO toDTO(Product src){
        return getModelMapper().map(src, ProductDTO.class);
    }

    private ProductListingDTO toListingDTO(Product src) {
        return getModelMapper().map(src, ProductListingDTO.class);
    }
}
