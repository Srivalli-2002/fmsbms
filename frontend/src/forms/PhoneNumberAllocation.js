import React, { useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import SystemAdminService from '../services/SystemAdminService';
import 'bootstrap/dist/css/bootstrap.min.css';
import './PhoneNumberAllocation.css';

const PhoneNumberAllocation = () => {
  const [username, setUsername] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");
  const formRef = useRef(null);

  const handleSubmit = async (event) => {
    event.preventDefault();
    setMessage("");
    setError("");

    const request = {
      username: username,
      phoneNumber: phoneNumber
    };

    try {
      const response = await SystemAdminService.allocatePhoneNumber(request);
      setMessage(response);
      formRef.current.reset(); // Reset form fields after successful submission
    } catch (err) {
      setError("Failed to allocate phone number");
    }
  };

  return (
    <div className="container mt-5 pt-3">
      <h2 className="pb-3">PHONE NUMBER ALLOCATION</h2>
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
            <label htmlFor="phoneNumber" className="form-label">Phone Number:</label>
            <input
              type="text"
              className="form-control"
              id="phoneNumber"
              value={phoneNumber}
              onChange={(e) => setPhoneNumber(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="btn btn-default">ALLOCATE</button>
        </form>
      </div>
      {message && <p className="mt-3 alert alert-success">{message}</p>}
      {error && <p className="mt-3 alert alert-danger">{error}</p>}
    </div>
  );
};

export default PhoneNumberAllocation;