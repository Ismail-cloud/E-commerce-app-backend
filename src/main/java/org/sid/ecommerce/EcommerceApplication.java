package org.sid.ecommerce;

import net.bytebuddy.utility.RandomString;
import org.sid.ecommerce.dao.CategoryRepository;
import org.sid.ecommerce.dao.ProductRepository;
import org.sid.ecommerce.entities.Category;
import org.sid.ecommerce.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Random;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoyRepository;
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;



    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Product.class);
        repositoryRestConfiguration.exposeIdsFor(Category.class);

        categoyRepository.save( new Category(null, "laptops", null,null, null));
        categoyRepository.save( new Category(null, "printers", null, null, null));
        categoyRepository.save( new Category(null, "smartphones", null, null, null));

        Random random =new Random();
        categoyRepository.findAll().forEach(c -> {
            for (int i = 0; i < 10; i++) {
                Product p= new Product();
                p.setName(RandomString.make(18));
                p.setCurrentPrice(100 + random.nextInt(1000));
                p.setAvailable(random.nextBoolean());
                p.setPromotion(random.nextBoolean());
                p.setSelected(random.nextBoolean());
                p.setCategory(c);
                p.setPhotoName("unknown.png");
                productRepository.save(p);
            }
            System.out.println(c.getName());

        });
    }
}
