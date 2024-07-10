import FamilyHeadService from '../services/FamilyHeadService';
import AuthService from '../services/AuthService';
import { Link } from 'react-router-dom';
import { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './FamilyHeadService.css';

const Family = () => {
  const [members, setMembers] = useState([]);

  useEffect(() => {
    fetchMembers();
  }, []);

  const fetchMembers = () => {
    const username = AuthService.getCurrentUser().username;
    console.log(username);
    FamilyHeadService.getAllMembersByUsername(username)
      .then(response => {
        setMembers(response);
        console.log(response);
      })
      .catch(error => {
        console.error('Error fetching members : ', error);
      });
  };

  return (
    <div className="container mt-5 pt-5">
      <h2 className="mb-5 pt-3">FAMILY MEMBERS</h2>
      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th>Member ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone Number</th>
          </tr>
        </thead>
        <tbody>
          {members.map(member => (
            <tr key={member.memberId}>
              <td>{member.memberId}</td>
              <td>{member.name}</td>
              <td>{member.email}</td>
              <td>{member.phoneNumber}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Family;