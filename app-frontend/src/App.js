import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'
import Login from './pages/login/Login'
import CreateProduct from './pages/home/CreateProduct';
import EditProduct from './pages/home/EditProduct';
import Home from './pages/home/Home';
import Register from './pages/login/Register';

function App() {
  return (
    <div className="App">
       <Router>
        <Routes>
          <Route path="/" element={<Login/>}/>
          <Route path="/home" element={<Home/>}/>
          <Route path="/add-product" element={<CreateProduct/>}/>
          <Route path="/edit-product" element={<EditProduct/>}/>
          <Route path="/register" element={<Register/>}/>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
