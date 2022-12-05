/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplerestfull.demo;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import model.Product;

/**
 *
 * @author savan_ks1jekz
 */
@RestController
public class Controller {
    private static Map<String, Product> productRepo = new HashMap<>();
    static{
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);
        
        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }
    
    @RequestMapping(value = "/products", method = RequestMethod.POST)
   public ResponseEntity<Object> createProduct(@RequestBody Product product) {
      productRepo.put(product.getId(), product);
      return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
   }
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String Id) {
        
        //menyatakan tidak bisa menghapus 
        if(!productRepo.containsKey(Id)){
            return new ResponseEntity<>("Product delete failed", HttpStatus.NOT_FOUND);
        }
        
        //menyatakan menghapus
        productRepo.remove(Id);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }
    
    //Untuk Update 
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("Id") String id, @RequestBody Product product) {
        
        //menyatakan belum update (put)
        if(!productRepo.containsKey(id)){
            return new ResponseEntity<>("Product update failed", HttpStatus.NOT_FOUND);
        }
        
        //Menyatakan berhasil(put)
        productRepo.remove(id);
        product.setId(id);
        productRepo.put(id, product);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/products")
   public ResponseEntity<Object> getProduct() {
      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
   }
}
