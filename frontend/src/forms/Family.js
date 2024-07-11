import React, { useState, useEffect } from 'react';
import FamilyHeadService from '../services/FamilyHeadService';
import AuthService from '../services/AuthService';
import 'bootstrap/dist/css/bootstrap.min.css';
import './FamilyHeadService.css';
import UpdateFamilyMember from './UpdateFamilyMember';

const Family = () => {
  const [members, setMembers] = useState([]);
  const [selectedMember, setSelectedMember] = useState(null);

  useEffect(() => {
    fetchMembers();
  }, []);

  const fetchMembers = () => {
    const username = AuthService.getCurrentUser().username;
    FamilyHeadService.getAllMembersByUsername(username)
      .then(response => {
        setMembers(response);
      })
      .catch(error => {
        console.error('Error fetching members: ', error);
      });
  };

  const handleUpdateClick = (member) => {
    setSelectedMember(member);
  };

  return (
    <div className="container mt-5 pt-5">
      <h2 className="mb-5 pt-3">FAMILY MEMBERS</h2>
      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th>MemberId</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {members.map(member => (
            <tr key={member.memeberId}>
              <td>{member.memeberId}</td>
              <td>{member.name}</td>
              <td>{member.email}</td>
              <td>{member.phoneNumber}</td>
              <td>
                <button className="btn btn-default" onClick={() => handleUpdateClick(member)}>
                  Update
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      {selectedMember && (
        <UpdateFamilyMember member={selectedMember} onClose={() => setSelectedMember(null)} onUpdate={fetchMembers} />
      )}
    </div>
  );
};

export default Family;
