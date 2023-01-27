import React, {useState} from 'react'
import { ProductService } from '../../service/ProductService';
import { useNavigate } from "react-router-dom";
import './product.css'
import NavBar from '../navbar/NavBar';


function CreateProduct() {
    const navigate = useNavigate()
    let [product, setProduct] = useState({
        productObj : {productName: "",
            productDescription: "",
            unitPrice: 0.0,
            availableCount: 0
                }
      },[]);

      const handleOnChange = (e) => {
        
        setProduct({
          ...product,
          productObj:{...product.productObj,[e.target.name] : e.target.value}
        });
      }

      const submitProduct =(productObj) =>{
        console.log(productObj);
        
        ProductService.createProduct(productObj).then(
            (res)=>{
              navigate("/home")


              setTimeout(() => {
                getNotification(productObj);
              }, 2000);
              
            },(error) => {
              
              if (error.code === "ERR_NETWORK") {
              
              } else {
                
              }
            }
          )
      };

      const getNotification = (productObj) =>{
        ProductService.getNotification(productObj).then(
          (res)=>{
            console.log("notification Received");
          },(error) => {
            
            if (error.code === "ERR_NETWORK") {
            
            } else {
              
            }
          }
        )
      }


  return (
    <><NavBar/>
    <div className='productContainer'>
      <div className="login">
       
            <h2>Add Product</h2>
            <input className='addProductInput' type="text" placeholder="Product Name" name='productName'
            onChange={(e) => handleOnChange(e)} />
            <input className='addProductInput' type="text" placeholder="Product Description" name='productDescription'
            onChange={(e) => handleOnChange(e)}/>
            <input className='addProductInput' type="number" placeholder="Unit Price" name='unitPrice'
            onChange={(e) => handleOnChange(e)}/>
            <input className='addProductInput' type="number" placeholder="availableCount" name='availableCount'
            onChange={(e) => handleOnChange(e)}/>
            <button className='addProductButton' onClick={() => submitProduct(product.productObj)}>Create</button>
            
          </div>
    </div>
    </>
  )
}

export default CreateProduct;
