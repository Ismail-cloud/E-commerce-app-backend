package org.sid.ecommerce.controller;

import org.sid.ecommerce.dao.ProductRepository;
import org.sid.ecommerce.entities.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

@CrossOrigin("*")
@RestController
public class CatalogueController {

    private static final String ENDPOINT_PRODUCT_PHOTOS = "/photoProduct/{id}";
    private static final String ENDPOINT_UPLOAD_PICTURE = "/uploadPicture/{id}";


    ProductRepository productRepository;

    public CatalogueController(ProductRepository productRepository) {
        this.productRepository= productRepository;
    }

    @GetMapping(path = ENDPOINT_PRODUCT_PHOTOS, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getProductPictures(@PathVariable Long id) throws IOException {
        Product p = productRepository.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+ "/E-commerce-App/products/"+p.getPhotoName()));
    }

    @PostMapping(path = ENDPOINT_UPLOAD_PICTURE)
    public void uploadPicture(MultipartFile file, @PathVariable Long id) throws IOException{
        Product p = productRepository.findById(id).get();
        p.setPhotoName(id+".png");
        Files.write(Paths.get(System.getProperty("user.home")+ "/E-commerce-App/products/" + p.getPhotoName()), file.getBytes());
        productRepository.save(p);
    }
}
