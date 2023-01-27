import React from 'react'
import api from './api'

export class ProductService{

    static getAllProducts(){
        return api.get(`/product/v1.0-product/get-all-products`);
    };

    static getProduct(productId){
        return api.get(`/product/v1.0-product/get-product/${productId}`);
    };

    static createProduct(product){
        console.log(product);
        return api.post(`/product/v1.0-product/create-product`, product);
    };

    static updateProduct(product){
        return api.put(`/product/v1.0-product/update-product`, product);
    };

    static deleteProduct(productId){
        console.log(productId);
        return api.delete(`/product/v1.0-product/delete-product/${productId}`);
    };

    static getNotification(product){
        console.log(product);
        return api.post(`/product/v1.0-product/get-notification`, product);
    };
  
    
}

export default ProductService;
