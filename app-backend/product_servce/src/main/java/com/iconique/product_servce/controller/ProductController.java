package com.iconique.product_servce.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iconique.product_servce.model.Message;
import com.iconique.product_servce.model.Product;
import com.iconique.product_servce.model.Status;
import com.iconique.product_servce.service.ProductService;
import com.iconique.user_servce.dto.ProductRequest;

@CrossOrigin
@RestController
@RequestMapping(value = "v1.0-product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
    private SimpMessagingTemplate template;
	
	@GetMapping("get-all-products")
    public ResponseEntity<List<Product>> getAllProducts(){
       return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
	
	@PostMapping("create-product")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest userRequest){
		Message message = new Message(userRequest.getProductName()+ " is created successfully", "Admin", new Date().toString());
    	System.out.println("scheduler started");
        template.convertAndSend("/chatroom/public", message);
         return new ResponseEntity<>(productService.createProduct(userRequest), HttpStatus.CREATED);
    }
	// GET API to GET USER
    @GetMapping("get-product/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId){
       return new ResponseEntity<>(productService.getProductById(Long.parseLong(productId)), HttpStatus.OK);
    }
    //PUT API to UPDATE USER
    @PutMapping("/update-product")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductRequest userRequest){
        return  new ResponseEntity<>(productService.updateProduct(userRequest),HttpStatus.OK);
    }

    // DELETE API to DELETE USER
    @DeleteMapping("/delete-product/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productId){
    	productService.deleteProduct(Long.parseLong(productId));
        return new ResponseEntity<>(Void.class, HttpStatus.OK);
    }
    
    @PostMapping("/get-notification")
    public ResponseEntity<?> getNotification(@RequestBody ProductRequest userRequest){
    	System.out.println(userRequest.getProductName());
		Message message = new Message(userRequest.getProductName()+ " is created successfully", "Admin", new Date().toString());
    	System.out.println("scheduler started"+ message.toString());
        template.convertAndSend("/chatroom/public", message);
        return new ResponseEntity<>(Void.class, HttpStatus.OK);
    }

}
