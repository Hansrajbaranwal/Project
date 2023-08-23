package com.ecommerce.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;

@Controller
public class AdminController {

    public String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String adminHome() {
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());// returns list which is used for loop in
        // categories.html
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String addCategoriesAdd(Model model) {
        model.addAttribute("category", new Category());// creating new category object and cat key from th obj
        // catAdd.html
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postCategoriesAdd(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category); // don't have access of service layer, auto-wired service
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable int id, Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "categoriesAdd";
        } else {
            return "404";
        }
    }

    // product section
    @GetMapping("/admin/products")
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String addProduct(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add") // runs on submit button at productsAdd.html
    public String postProductAdd(@ModelAttribute("productDTO") ProductDTO productDTO,
                                 @RequestParam("productImage") MultipartFile file, @RequestParam("imgName") String imgName)
            throws IOException {
        // converting product dto obj to product obj
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());
        String imageUUID;
        if (!file.isEmpty()) { // needs to work on false value of file->giving !file
            imageUUID = file.getOriginalFilename();
            // creating file path and name
            Path fileNameAndPath = Paths.get(uploadDirectory, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imgName;
        }
        product.setImageName(imageUUID);
        productService.addProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable long id, Model model) {
        Product product = productService.getProductById(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("productDTO", productDTO);
        return "productsAdd";
    }

}