package uz.pdp.appwarehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.domain.Category;
import uz.pdp.appwarehouse.exseption.ResourceNotFoundException;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.repository.CategoryRepository;
import uz.pdp.appwarehouse.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public ApiResponse add(CategoryDto dto) {
        if (categoryRepository.existsByName(dto.getName()))
            return new ApiResponse("Bunday nomli category mavjud", false);
        Category category = new Category();
        category.setName(dto.getName());
        if (dto.getParentId()!=null){
            Category parentCategory = categoryRepository.findById(dto.getParentId()).orElseThrow(() -> new ResourceNotFoundException("Category", "name", true));
            category.setParentCategory(parentCategory);
        }
        categoryRepository.save(category);
        return new ApiResponse("Saqlandi", true);
    }

    @Override
    public ApiResponse edet(Long id, CategoryDto dto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", true));
        category.setName(dto.getName());
        if (dto.getParentId()!=null){
            Category parentCategory = categoryRepository.findById(dto.getParentId()).orElseThrow(() -> new ResourceNotFoundException("Category", "name", true));
            category.setParentCategory(parentCategory);
        }
        categoryRepository.save(category);
        return new ApiResponse("Saqlandi", true);
    }

    @Override
    public ApiResponse enabled(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", true));
        if (category.isEnabled())
            category.setEnabled(false);
        category.setEnabled(true);
        categoryRepository.save(category);
        return new ApiResponse("Ozgartirildi", true);
    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> listCategory = new ArrayList<>();

        for (Category category : categories) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(category.getName());
            if (category.getParentCategory()!=null) {
                categoryDto.setParentName(category.getParentCategory().getName());
                categoryDto.setParentId(category.getParentCategory().getId());
            }
            category.setEnabled(category.isEnabled());
            listCategory.add(categoryDto);
        }

        return listCategory;
    }
}
