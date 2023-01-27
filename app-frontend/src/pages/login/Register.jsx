import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import UserService from '../../service/UserService'
import Alert from "react-bootstrap/Alert";

function Register() {

    let [userObj, setUserObj] = useState({
        credsObj : {firsname: "",
        lastname: "",
        email: "",
        username: "",
        password: ""
                
    }
      },[]);
    
    
        const navigate = useNavigate()
        const [user, setUser] = useState("");
        const [error, setError] = useState("");
        const [username, setUsername] = useState("");
    
    
        const handleOnChange = (e) => {
          console.log()
          setUserObj({
            ...userObj,
            credsObj:{...userObj.credsObj,[e.target.name] : e.target.value}
          });
        }
    
        const registerUser = () => {
          console.log("getToken "+userObj.credsObj.username);
    
          UserService.register(userObj.credsObj).then(
            (res) => {
              console.log(res.data);
              navigate("/")
            },
            (error) => {
              if (error.code === "ERR_NETWORK") {
                navigate("/register")
                setError("Netrowk Failed");
              } else {
              }
              setError("Registration Failed");
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
            <h2>User Registration</h2>
           
            <input required type="text" placeholder="First Name"
            onChange={(e) => handleOnChange(e)} name="firsname" />
            <input required type="text" placeholder="Last Name" 
            onChange={(e) => handleOnChange(e)} name="lastname"/>
            <input required type="email" placeholder="Email Address" 
            onChange={(e) => handleOnChange(e)} name="email"/>
            <input required type="text" placeholder="User Name" 
            onChange={(e) => handleOnChange(e)} name="username"/>
            <input required type="password" placeholder="Password" 
            onChange={(e) => handleOnChange(e)} name="password"/>
            <button onClick={() => registerUser()}>Submit</button>
            
          </div>
        )}
    
        </div>
  )
}

export default Register

