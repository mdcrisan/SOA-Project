import React from 'react'

function TableContent({allProducts, editProductDetail, addProduct, deleteProduct}) {
    
  return (
    <div className="card" align="center">
      <div className="info">
        <h1>Iconique Home Page</h1>
      </div>
      <div align="right">
      <button onClick={() =>addProduct()} className='addButton'>Add</button>
      </div>
      <table>
      <thead>
        <tr>
          
          <th>Product Name</th>
          <th>Product Description</th>
          <th>Unit Price</th>
          <th>Quantity</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        {allProducts.map((item) => (
            <tr>
                <td>{item.productName}</td>
                <td>{item.productDescription}</td>
                <td>{item.unitPrice}</td>
                <td>{item.availableCount}</td>
                <td><button onClick={() =>editProductDetail(item.id)} className='editButton'>Edit</button>
                <button onClick={() =>deleteProduct(item.id)} className='deleteButton'>Delete</button></td>
            </tr>
        ))}
        
      </tbody>
    </table>
      </div>
  )
}

export default TableContent
