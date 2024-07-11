import React, { useState, useRef, useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import FamilyHeadService from '../services/FamilyHeadService';
import 'bootstrap/dist/css/bootstrap.min.css';
import './MobilePlans.css';

const BuySim = () => {
  const [username, setUsername] = useState("");
  const [familyHeadName, setFamilyHeadName] = useState("");
  const [email, setEmail] = useState("");
  const [address, setAddress] = useState("");
  const [planType, setPlanType] = useState("");
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");
  const formRef = useRef(null);

  const handleSubmit = async (event) => {
    event.preventDefault();
    setMessage("");
    setError("");

    const familyData = {
      username,
      familyHeadName,
      email,
      address,
      planType
    };

    try {
      const response = await FamilyHeadService.buySim(familyData);
      setMessage(response);
      formRef.current.reset(); // Reset form fields after successful submission
    } catch (err) {
      setError("Failed to purchase SIM");
    }
  };

  return (
    <div className="container mt-5 pt-3">
      <h2 className="pb-3">BUY SIM CARD</h2>
      <div className="card card-container mt-3 p-3">
        <form onSubmit={handleSubmit} ref={formRef}>
          <div className="mb-3">
            <label htmlFor="username" className="form-label">Username:</label>
            <input
              type="text"
              className="form-control"
              id="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="familyHeadName" className="form-label">Family Head Name:</label>
            <input
              type="text"
              className="form-control"
              id="familyHeadName"
              value={familyHeadName}
              onChange={(e) => setFamilyHeadName(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">Email:</label>
            <input
              type="email"
              className="form-control"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="address" className="form-label">Address:</label>
            <input
              type="text"
              className="form-control"
              id="address"
              value={address}
              onChange={(e) => setAddress(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="planType" className="form-label">Plan Type:</label>
            <input
              type="text"
              className="form-control"
              id="planType"
              value={planType}
              onChange={(e) => setPlanType(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="btn btn-default">BUY SIM</button>
        </form>
      </div>
      {message && <p className="mt-3 alert alert-success">{message}</p>}
      {error && <p className="mt-3 alert alert-danger">{error}</p>}
    </div>
  );
};

export default BuySim;