import React, { useState, useEffect } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
 
import AuthService from "./services/AuthService";
 
import Login from "./components/Login";
import Register from "./components/Register";
import Home from "./components/Home";
import Profile from "./components/Profile";
 
import EventBus from "./common/EventBus";

import UserManagement from "./forms/UserManagement";
import UpdateUser from "./forms/UpdateUser";
import MobilePlans from "./forms/MobilePlans";
import PhoneNumberAllocation from "./forms/PhoneNumberAllocation";
import BuySim from "./forms/BuySim";
import Family from "./forms/Family";
import Payments from "./forms/Payments";
import Bills from "./forms/Bills";
 
const App = () => {
  const [showSystemAdmin, setShowSystemAdmin] = useState(false);
  const [showCustomerSupport, setShowCustomerSupport] = useState(false);
  const [showFamilyHead, setShowFamilyHead] = useState(false);
  const [currentUser, setCurrentUser] = useState(undefined);
 
  useEffect(() => {
    const user = AuthService.getCurrentUser();
 
    if (user) {
      setShowSystemAdmin(user.role.includes("ROLE_SYSTEM_ADMIN"));
      setCurrentUser(user);
      setShowCustomerSupport(user.role.includes("ROLE_CUSTOMER_SUPPORT"));
      setCurrentUser(user);
      console.log(currentUser);
      setShowFamilyHead(user.role.includes("ROLE_FAMILY_HEAD"));
      setCurrentUser(user);
 
    }
    EventBus.on("logout", () => {
      logOut();
    });
 
    return () => {
      EventBus.remove("logout");
    };
  }, []);
 
  const logOut = () => {
    AuthService.logout();
    setShowSystemAdmin(false);
    setShowCustomerSupport(false);
    setShowFamilyHead(false);
    setCurrentUser(undefined);
  };
 
  return (
    <div>
      <nav className="navbar fixed-top navbar-expand custom-navbar">
        <Link to={"/"} className="navbar-brand">
        FAMILY MOBILE SERVICES BILLING MANAGEMENT SYSTEM
        </Link>
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/home"} className="nav-link">
              Home
            </Link>
          </li>
 
          {showSystemAdmin && (
            <li className="nav-item">
              <Link to={"/usermanagement"} className="nav-link">
              User Dashboard
              </Link>
            </li>
          )}
 
          {showSystemAdmin && (
            <li className="nav-item">
              <Link to={"/mobileplans"} className="nav-link">
                Plans Dashboard
              </Link>
            </li>
          )}

          {showSystemAdmin && (
            <li className="nav-item">
              <Link to={"/phonenumberallocation"} className="nav-link">
                Phonenumber Dashboard
              </Link>
            </li>
          )}
 
          {showCustomerSupport && (
            <li className="nav-item">
              <Link to={"/bills"} className="nav-link">
                Bill Dashboard
              </Link>
            </li>
          )}
 
          {showFamilyHead && (
            <li className="nav-item">
              <Link to={"/buysim"} className="nav-link">
                Sim Dashboard
              </Link>
            </li>
          )}    
 
          {showFamilyHead && (
            <li className="nav-item">
              <Link to={"/family"} className="nav-link">
                Family Dashboard
              </Link>
            </li>
          )}      
 
          {showFamilyHead && (
            <li className="nav-item">
              <Link to={"/payments"} className="nav-link">
                Payment Dashboard
              </Link>
            </li>
          )}    
          </div>
 
        {currentUser ? (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/profile"} className="nav-link">
                {currentUser.username}
              </Link>
            </li>
            <li className="nav-item">
              <a href="/login" className="nav-link" onClick={logOut}>
                Logout
              </a>
            </li>
          </div>
        ) : (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/login"} className="nav-link">
                Sign in
              </Link>
            </li>
 
            <li className="nav-item">
              <Link to={"/register"} className="nav-link">
                Sign up
              </Link>
            </li>
           
          </div>
        )}
      </nav>
 
      <div className="container mt-3">
        <Routes>
          <Route path="/" element={<Home/>} />
          <Route path="/home" element={<Home/>} />
          <Route path="/login" element={<Login/>} />
          <Route path="/register" element={<Register/>} />
          <Route path="/profile" element={<Profile/>} />

          <Route path="/usermanagement" element={<UserManagement/>} />
          <Route path="/update-userrole/:userId" element={<UpdateUser/>} />
          <Route path="/mobileplans" element={<MobilePlans/>} />
          <Route path="/phonenumberallocation" element={<PhoneNumberAllocation/>}/>

          <Route path="/buysim" element={<BuySim/>}/>
          <Route path="/family" element={<Family/>}/>
          <Route path="/payments" element={<Payments/>}/>
          <Route path="/bills" element={<Bills/>}/>
        </Routes>
      </div>
 
    </div>
  );
};
 
export default App;