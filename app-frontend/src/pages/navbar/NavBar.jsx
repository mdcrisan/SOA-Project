import { useEffect, useState } from "react";
import "./NavBarStyles.css";
import Notification from "../../img/notification.svg";
import Message from "../../img/message.svg";
import Settings from "../../img/settings.svg";
import User1 from "../../img/user1.jpeg";
import { useNavigate } from "react-router-dom";
import { over } from 'stompjs';
import SockJS from 'sockjs-client';

var stompClient = null;
function NavBar() {
  const [meesages, setMessages] = useState([]);
  const [notificationOpen, setNotificationOpen] = useState(false);
  const [messageOpen, setMessageOpen] = useState(false);
  const [profilePopUp, setProfilePopUp] = useState(false);
  const navigate = useNavigate()
  const loggedUser = window.sessionStorage.getItem("user");
  const [publicChats, setPublicChats] = useState([]);
  const [userData, setUserData] = useState({
    message: '',
    user: '',
    date: ''
  });

  const onError = (err) => {
    console.log(err);
  }

  const connect = () => {
    let Sock = new SockJS('http://localhost:9001/ws');
    stompClient = over(Sock);
    stompClient.connect({}, onConnected, onError);
  }

  const onConnected = () => {
    setUserData({ ...userData, "connected": true });
    stompClient.subscribe('/chatroom/public', onMessageReceived);
    //stompClient.subscribe('/user/'+userData.username+'/private', onPrivateMessage);
    //userJoin();
  }

  const onMessageReceived = (payload) => {
    var payloadData = JSON.parse(payload.body);
    publicChats.push(payloadData);
    setPublicChats([...publicChats]);
  }

  useEffect(() => {
    connect();
    setMessages(['Hello!',
      'Contact Support Center at +40 744 123 456.']);
  }, []);

  const logout = () => {
    window.sessionStorage.clear();
    navigate("/")
  }

  const myComponentStyle = {
    border: 'solid'
  }

  return (
    <>
      <nav className="navbar">
        <a href="/home">
          <svg
            id="logo-15"
            width="49"
            height="48"
            viewBox="0 0 49 48"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            {" "}
            <path
              d="M24.5 12.75C24.5 18.9632 19.4632 24 13.25 24H2V12.75C2 6.53679 7.03679 1.5 13.25 1.5C19.4632 1.5 24.5 6.53679 24.5 12.75Z"
              class="ccustom"
              fill="#fff"
            ></path>{" "}
            <path
              d="M24.5 35.25C24.5 29.0368 29.5368 24 35.75 24H47V35.25C47 41.4632 41.9632 46.5 35.75 46.5C29.5368 46.5 24.5 41.4632 24.5 35.25Z"
              class="ccustom"
              fill="#fff"
            ></path>{" "}
            <path
              d="M2 35.25C2 41.4632 7.03679 46.5 13.25 46.5H24.5V35.25C24.5 29.0368 19.4632 24 13.25 24C7.03679 24 2 29.0368 2 35.25Z"
              class="ccustom"
              fill="#fff"
            ></path>{" "}
            <path
              d="M47 12.75C47 6.53679 41.9632 1.5 35.75 1.5H24.5V12.75C24.5 18.9632 29.5368 24 35.75 24C41.9632 24 47 18.9632 47 12.75Z"
              class="ccustom"
              fill="#fff"
            ></path>{" "}
          </svg>
        </a>

        <div className="icons">

          <div className="icon">
            <img src={Notification} className="iconImg" alt="" />
            <div className="counter" onClick={() => {if(publicChats.length > 0) {setNotificationOpen(!notificationOpen) }}}>{publicChats.length}</div>

            <div style={myComponentStyle} className={`dropdown-menu ${notificationOpen ? 'active' : 'inactive'}`} >
              <ul>{publicChats.map((item, index) => (<li> {item.message + " " + item.date}</li>))}</ul>
            </div>

          </div>
          <div className="icon">
            <img src={Message} className="iconImg" alt="" />
            <div className="counter" onClick={() => { setMessageOpen(!messageOpen) }}>{meesages.length}</div>
            <div style={myComponentStyle} className={`dropdown-menu ${messageOpen ? 'active' : 'inactive'}`} >
              <ul>{meesages.map((item) => (<li>{item}</li>))}</ul>
            </div>

          </div>
          <div className="icon">
            <img src={Settings} className="iconImg" alt="" />
          </div>
          <div className="icon" onClick={() => { setProfilePopUp(!profilePopUp) }}>
            <img src={User1} className="profileImage" alt="" />
            <div style={myComponentStyle} className={`dropdown-menu ${profilePopUp ? 'active' : 'inactive'}`} >
              <ul>
                <li>{loggedUser}</li>
                <li onClick={() => logout()}>Log Out</li>
              </ul>
            </div>
          </div>
        </div>
      </nav>
    </>
  );
}

export default NavBar;
