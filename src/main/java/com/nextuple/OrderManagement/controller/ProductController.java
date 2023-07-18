package com.nextuple.OrderManagement.controller;

import com.nextuple.OrderManagement.dto.ProductInputDto;
import com.nextuple.OrderManagement.dto.ProductOutputDto;
import com.nextuple.OrderManagement.entity.Product;
import com.nextuple.OrderManagement.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<List<ProductInputDto>>  getAllItems()
    {
        List<ProductInputDto> products=productService.getAllItems().stream().map(
                product -> modelMapper.map(product, ProductInputDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProductOutputDto> getItemByName(@PathVariable String name)
    {
        Product product=productService.getItemByName(name);

        //convert to DTO
        ProductOutputDto productInputDto =modelMapper.map(product, ProductOutputDto.class);
        return new ResponseEntity<>(productInputDto,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<ProductOutputDto>> addItem(@RequestBody List<ProductInputDto> productsDto)
    {

        //convert DTO to entity
        List<Product> products=productsDto.stream().map(productInputDto ->
                modelMapper.map(productInputDto,Product.class)).collect(Collectors.toList());

        //convert entity to DTO
        List<ProductOutputDto> addedProducts= productService.addItem(products).stream().map(
                product -> modelMapper.map(product, ProductOutputDto.class)).collect(Collectors.toList());

        return new ResponseEntity<>(addedProducts,HttpStatus.CREATED);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteProduct(@PathVariable String name)
    {
        String result=productService.deleteItem(name);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody List<ProductInputDto> productsInputDto)
    {
        List<Product> product=new ArrayList<>();
        //convert DTO to entity
        for(ProductInputDto productInputDto:productsInputDto)
        {
            Product p=modelMapper.map(productInputDto,Product.class);
            product.add(p);
        }

        List<Product> updatedProducts=productService.updateItem(product);

        // convert entity to DTO

        List<ProductOutputDto> updatedProductsOutputDto= new ArrayList<>();

        for (Product p:updatedProducts )
        {
            ProductOutputDto productOutputDto=modelMapper.map(p,ProductOutputDto.class);
            updatedProductsOutputDto.add(productOutputDto);
        }

        return new ResponseEntity<>(updatedProductsOutputDto,HttpStatus.ACCEPTED);
    }

}
