package pl.javastart.restoffers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {
    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/api/categories/names")
    public List<String> getNames() {
        List<Category> categoryList=categoryRepository.findAll();
        List<String > names=new ArrayList<>();
        for (Category category : categoryList) {
            names.add(category.getName());
        }

        return names;
    }

    @GetMapping("/api/categories")
    public List<Category> getCategories() {

        return categoryRepository.findAll();
    }

    @PostMapping("/api/categories")
    public Category insert(@RequestBody Category category) {
        categoryRepository.save(category);
        return category;
    }

    @DeleteMapping("/api/categories/{id}")
    public void delete(@PathVariable Long id) {
        categoryRepository.deleteById(id);
    }
}
