package uz.pdp.appwarehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.domain.Attachment;
import uz.pdp.appwarehouse.domain.Category;
import uz.pdp.appwarehouse.domain.Measurement;
import uz.pdp.appwarehouse.domain.Products;
import uz.pdp.appwarehouse.exseption.ResourceNotFoundException;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.repository.AttachmentRepository;
import uz.pdp.appwarehouse.repository.CategoryRepository;
import uz.pdp.appwarehouse.repository.MeasurementRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;
import uz.pdp.appwarehouse.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    AttachmentRepository attachmentRepository;

    @Override
    public ApiResponse add(ProductDto dto) {
       if (productRepository.existsByName(dto.getName()))
           return new ApiResponse("Bunday mahsulot bor", false);
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Product", "category", true));
        Measurement measurement = measurementRepository.findById(dto.getMeasurementId()).orElseThrow(() -> new ResourceNotFoundException("Product", "measurement", true));
        Attachment attachment = attachmentRepository.findById(dto.getAttachmentId()).orElseThrow(() -> new ResourceNotFoundException("Product", "attachment", true));
        Products product = new Products(
                dto.getName(),
                category,
                attachment,
                measurement
        );
        productRepository.save(product);
        return new ApiResponse("Saqlandi", true);
    }

    @Override
    public ApiResponse edet(ProductDto dto, Long id) {
        Products product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "product", true));
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Product", "category", true));
        Measurement measurement = measurementRepository.findById(dto.getMeasurementId()).orElseThrow(() -> new ResourceNotFoundException("Product", "measurement", true));
        Attachment attachment = attachmentRepository.findById(dto.getAttachmentId()).orElseThrow(() -> new ResourceNotFoundException("Product", "attachment", true));

        product.setName(dto.getName());
        product.setCategory(category);
        product.setMeasurement(measurement);
        product.setPhoto(attachment);

        productRepository.save(product);
        return new ApiResponse("Edet", true);
    }

    @Override
    public List<Products> get() {
        List<Products> productList = productRepository.findAll();
        return productList;
    }

    @Override
    public ApiResponse delete(Long id) {
        try {
            productRepository.deleteById(id);
            return new ApiResponse("Delete product", true);
        }catch (Exception e){
            return new ApiResponse("No deleted product", false);
        }
    }

    @Override
    public ApiResponse completed(Long id) {
        Products product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", true));
        if (product.isEnabled()){
            product.setEnabled(false);
        }else {
            product.setEnabled(true);
        }
        productRepository.save(product);
        return new ApiResponse("Completed", true);

    }

    @Override
    public Products getOne(Long id) {
        Products product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", true));
        return product;
    }
}
