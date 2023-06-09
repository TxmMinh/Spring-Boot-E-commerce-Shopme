package com.shopme.admin.product;

import com.shopme.common.entity.Brand;
import com.shopme.common.entity.Category;
import com.shopme.common.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateProduct() {
        Brand brand = entityManager.find(Brand.class, 10);
        Category category = entityManager.find(Category.class, 15);

        Product product = new Product();
        product.setName("Samsung Galaxy A32");
        product.setAlias("samsung_galaxy_a32");
        product.setShortDescription("A good smartphone from samsung");
        product.setFullDescription("This is a very good smartphone full description");

        product.setBrand(brand);
        product.setCategory(category);

        product.setPrice(456);
        product.setCreatedTime(new Date());
        product.setUpdatedTime(new Date());

        Product savedProduct = repo.save(product);

        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateProduct2() {
        Brand brand = entityManager.find(Brand.class, 37);
        Category category = entityManager.find(Category.class, 5);

        Product product = new Product();
        product.setName("Acer Aspire Desktop");
        product.setAlias("acer_aspire_desktop");
        product.setShortDescription("Short description for Acer Aspire");
        product.setFullDescription("Full description for Acer Aspire");

        product.setBrand(brand);
        product.setCategory(category);

        product.setPrice(678);
        product.setCost(600);
        product.setEnabled(true);
        product.setInStock(true);

        product.setCreatedTime(new Date());
        product.setUpdatedTime(new Date());

        Product savedProduct = repo.save(product);

        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllProducts() {
        Iterable<Product> iterableProducts = repo.findAll();
        iterableProducts.forEach(System.out :: println);
    }


    @Test
    public void testGetProduct() {
        Integer id = 2;
        Product product = repo.findById(id).get();
        System.out.println(product);

        assertThat(product).isNotNull();
    }

    @Test
    public void testUpdateProduct() {
        Integer id = 1;
        Product product = repo.findById(id).get();
        product.setPrice(499);

        repo.save(product);
        Product updatedProduct = entityManager.find(Product.class, id);
        assertThat(updatedProduct.getPrice()).isEqualTo(499);
    }

    @Test
    public void testDeleteProduct() {
        Integer id = 3;
        repo.deleteById(3);

        Optional<Product> result = repo.findById(id);

        System.out.println(result.isPresent());
        assertThat(!result.isPresent());
    }

    @Test
    public void testSaveProductWithImages() {
        Integer productId = 1;
        Product product = repo.findById(productId).get();

        product.setMainImage("main image.jpg");
        product.addExtraImage("extra image 1.png");
        product.addExtraImage("extra_image_2.png");
        product.addExtraImage("extra-image3.png");

        Product savedProduct = repo.save(product);

        assertThat(savedProduct.getImages().size()).isEqualTo(3);
    }

    @Test
    public void testSaveProductWithDetails() {
        Integer productId = 1;
        Product product = repo.findById(productId).get();

        product.addDetail("Device Memory", "128 GB");
        product.addDetail("CPU Model", "MediaTek");
        product.addDetail("OS", "Android 10");

        Product savedProduct = repo.save(product);
        assertThat(savedProduct.getDetails()).isNotEmpty();
    }
}
