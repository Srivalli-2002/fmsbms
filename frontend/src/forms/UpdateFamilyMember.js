import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import FamilyHeadService from '../services/FamilyHeadService';

const UpdateFamilyMember = ({ member, onClose, onUpdate }) => {
    const [name, setName] = useState(member.name);
    const [email, setEmail] = useState(member.email);
    const [phoneNumber, setPhoneNumber] = useState(member.phoneNumber);
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");
  
    const handleSubmit = async (event) => {
      event.preventDefault();
      setMessage("");
      setError("");
  
      const memberData = {
        memberId: member.memeberId,
        username: member.family.username, 
        name,
        email,
        phoneNumber
      };
  
      try {
        await FamilyHeadService.updateMember(memberData);
        console.log(memberData);
        setMessage("Member updated successfully");
        onUpdate(); // Refresh the members list
        onClose(); // Close the form
      } catch (err) {
        setError("Failed to update member");
      }
    };
  
    return (
      <div className="update-member-form-container">
        <div className="card card-container mt-3 p-3">
          <h2>UPDATE FAMILY MEMBER</h2>
          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label htmlFor="name" className="form-label">Name:</label>
              <input
                type="text"
                className="form-control"
                id="name"
                value={name}
                onChange={(e) => setName(e.target.value)}
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
            <button type="submit" className="btn btn-default">UPDATE</button>
            <button type="button" className="btn btn-secondary ml-2" onClick={onClose}>Cancel</button>
          </form>
          {message && <p className="mt-3 alert alert-success">{message}</p>}
          {error && <p className="mt-3 alert alert-danger">{error}</p>}
        </div>
      </div>
    );
  };
  
  export default UpdateFamilyMember;