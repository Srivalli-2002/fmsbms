import React, { useState, useRef, useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import FamilyHeadService from '../services/FamilyHeadService';
import 'bootstrap/dist/css/bootstrap.min.css';
import './MobilePlans.css';

const BuySim = () => {
  const [newSim, setNewSim] = useState({ username: '', familyHeadName: '', email: '', address: '', planType: '' });
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState('');

  const form = useRef();
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewSim({ ...newSim, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage('');

    FamilyHeadService.buySim(newSim)
      .then(() => {
        setMessage('Sim brought successfully');
        setNewSim({  username: '', familyHeadName: '', email: '', address: '', planType: '' });
      })
      .catch(error => {
        console.error('Error adding sim : ', error);
        setMessage('An error occurred while adding the sim');
      })
      .finally(() => {
        setLoading(false);
      });
  };

  return (
    <div className="container mt-5 pt-3">
      <h2 className="pb-3">BUY SIM</h2>
      <div className="card card-container mt-3 p-3">
        <form onSubmit={handleSubmit} ref={form}>
          <div className="mb-3">
            <label htmlFor="username" className="form-label">Username:</label>
            <input
              type="text"
              className="form-control"
              id="username"
              name="username"
              value={newSim.username}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">Email:</label>
            <input
              type="email"
              className="form-control"
              id="email"
              name="email"
              value={newSim.email}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="address" className="form-label">Address:</label>
            <input
              type="text"
              className="form-control"
              id="address"
              name="address"
              value={newSim.address}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="planType" className="form-label">Plan Type:</label>
            <input
              type="text"
              className="form-control"
              id="planType"
              name="planType"
              value={newSim.planType}
              onChange={handleInputChange}
              required
            />
          </div>
          <button type="submit" className="btn btn-default" disabled={loading}>
            {loading ? 'Adding...' : 'BUY SIM'}
          </button>
        </form>
      </div>
    </div>
  );
};

export default BuySim;