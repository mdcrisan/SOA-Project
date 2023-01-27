import React from 'react'
import "./Login.css";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import UserService from '../../service/UserService'
import Alert from "react-bootstrap/Alert";

function Login() {

  let [userObj, setUserObj] = useState({
        credsObj : {username: "",
                    password: ""
                  }
  },[]);


    const navigate = useNavigate()
    const [user, setUser] = useState("");
    const [error, setError] = useState("");

    const handleOnChange = (e) => {
      console.log()
      setUserObj({
        ...userObj,
        credsObj:{...userObj.credsObj,[e.target.name] : e.target.value}
      });
    }

    const getToken = () => {
      console.log("getToken "+userObj.credsObj.username);

      UserService.login(userObj.credsObj).then(
        (res) => {
          console.log(res.data);
          window.sessionStorage.setItem("user", userObj.credsObj.username);
          navigate("/home");
        },
        (error) => {
          if (error.code === "ERR_NETWORK") {
            navigate("/")
            setError("Netrowk Failed");
          } else {
            setError("Login Failed");
          }
        }
      );

      setTimeout(() => {
        setError("")
      }, 2000);
    }



  return (
    <div className="container body">
       <Alert key="danger" variant="danger">
            {error}
          </Alert>
        {user ? (
             navigate("/home")
        ):(
            <div className="login">
            <h2 className='app-name'>Iconique</h2>
           
            <input required type="text" placeholder="username"
            onChange={(e) => handleOnChange(e)} name="username" />
            <input required type="password" placeholder="password" 
            onChange={(e) => handleOnChange(e)} name="password"/>
            <button onClick={() => getToken()}>Login</button>
            <br/>
            <a className="register-link" href='/register'>Register Here</a>
          </div>
        )}
    
        </div>
  )
}

export default Login;
