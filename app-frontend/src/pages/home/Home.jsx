import React, { useState, useEffect } from "react";
import NavBar from "../navbar/NavBar";
import "./home.css";
import { useNavigate } from "react-router-dom";
import ProductService from "../../service/ProductService";
import EditProduct from "./EditProduct";
import TableContent from "./TableContent";

function Home() {
  const navigate = useNavigate();
  const [cardContent, setCardContent] = useState();
  const [allProducts, setAllProducts] = useState([]);

  const callProductAPI = () => {
    ProductService.getAllProducts().then(
      (res) => {
        setCardContent(
          <TableContent
            allProducts={res.data}
            editProductDetail={editProductDetail}
            addProduct={addProduct}
            deleteProduct={deleteProduct}
          />
        );
      },
      (error) => {
        if (error.code === "ERR_NETWORK") {
        } else {
        }
      }
    );
  };

  const addProduct = () => {
    navigate("/add-product");
  };

  const editProductDetail = (productId) => {
    ProductService.getProduct(productId).then(
      (res) => {
        setEditProdctData(res.data);
      },
      (error) => {
        if (error.code === "ERR_NETWORK") {
        } else {
        }
      }
    );
  };

  const refreshPage = () => {
    window.location.reload();
  };

  const deleteProduct = (productId) => {
    ProductService.deleteProduct(productId).then(
      (res) => {
        navigate("/home");
      },
      (error) => {
        if (error.code === "ERR_NETWORK") {
        } else {
        }
      }
    );
    refreshPage();
  };

  const setEditProdctData = (productData) => {
    setCardContent(<EditProduct productData={productData} />);
  };

  useState(() => {
    callProductAPI();
  });

  return (
    <>
      <NavBar
      />
      {cardContent}
    </>
  );
}

export default Home;
