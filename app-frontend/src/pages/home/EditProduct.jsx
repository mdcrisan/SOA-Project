import React, {useState} from 'react'
import { ProductService } from '../../service/ProductService';
import { useNavigate } from "react-router-dom";
import './product.css'


const EditProduct = ({productData}) => {
    const navigate = useNavigate()
    let [product, setProduct] = useState({
        productObj : {
            id: productData.id,
            productName: productData.productName,
            productDescription: productData.productDescription,
            unitPrice: productData.unitPrice,
            availableCount: productData.availableCount
                }
      },[]);

      const handleOnChange = (e) => {
        
        setProduct({
          ...product,
          productObj:{...product.productObj,[e.target.name] : e.target.value}
        });
      };

      const refreshPage = ()=>{
        window.location.reload();
     }

      const submitProduct =(productObj) =>{
        
        ProductService.updateProduct(productObj).then(
            (res)=>{
              navigate("/home");
            },(error) => {
              
              if (error.code === "ERR_NETWORK") {
              
              } else {
                
              };
            }
          );
          refreshPage();
      };


  return (
    <>
    
    <div className='productContainer'>
      <div className="login">
       
            <h2>Edit Product</h2>
            <input  hidden className='addProductInput' type="text" placeholder="Product Name" name='id'
            defaultValue={productData.id}
            onChange={(e) => handleOnChange(e)} />
            <input className='addProductInput' type="text" placeholder="Product Name" name='productName'
            defaultValue={productData.productName}
            onChange={(e) => handleOnChange(e)} />
            <input className='addProductInput' type="text" placeholder="Product Description" name='productDescription'
            defaultValue={productData.productDescription}
            onChange={(e) => handleOnChange(e)}/>
            <input className='addProductInput' type="number" placeholder="Unit Price" name='unitPrice'
            defaultValue={productData.unitPrice}
            onChange={(e) => handleOnChange(e)}/>
            <input className='addProductInput' type="number" placeholder="availableCount" name='availableCount'
             defaultValue={productData.availableCount}
            onChange={(e) => handleOnChange(e)}/>
            <button className='addProductButton' onClick={() => submitProduct(product.productObj)}>Update</button>
            
          </div>
    </div>
    </>
  );
};

export default EditProduct;
