import React, { useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import SystemAdminService from '../services/SystemAdminService';
import 'bootstrap/dist/css/bootstrap.min.css';
import './MobilePlans.css';

const PhoneNumberAllocation = () => {
  const [newPhoneNumber, setNewPhoneNumber] = useState({ username: '', phoneNumber: ''});
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState('');

  const form = useRef();
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewPhoneNumber({ ...newPhoneNumber, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage('');

    SystemAdminService.allocatePhoneNumber(newPhoneNumber)
      .then(() => {
        setMessage('Phone number allocated successfully');
        setNewPhoneNumber({ username: '', phoneNumber: ''});
      })
      .catch(error => {
        console.error('Error allocating phone number : ', error);
        setMessage('An error occurred while allocating the phone number');
      })
      .finally(() => {
        setLoading(false);
      });
  };

  return (
    <div className="container mt-5 pt-3">
      <h2 className="pb-3">ALLOCATE PHONENUMBER</h2>
      <div className="card card-container mt-3 p-3">
        <form onSubmit={handleSubmit} ref={form}>
          <div className="mb-3">
            <label htmlFor="username" className="form-label">Username:</label>
            <input
              type="text"
              className="form-control"
              id="username"
              name="username"
              value={newPhoneNumber.username}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="phoneNumber" className="form-label">Phone Number:</label>
            <input
              type="number"
              className="form-control"
              id="phoneNumber"
              name="phoneNumber"
              value={newPhoneNumber.phoneNumber}
              onChange={handleInputChange}
              required
            />
          </div>
          <button type="submit" className="btn btn-default" disabled={loading}>
            {loading ? 'Adding...' : 'ALLOCATE PHONE NUMBER'}
          </button>
        </form>
      </div>
    </div>
  );
};

export default PhoneNumberAllocation;