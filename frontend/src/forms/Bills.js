import React, { useState, useRef, useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import CustomerSupportService from '../services/CustomerSupportService';
import 'bootstrap/dist/css/bootstrap.min.css';

const Bills = () => {
  const [newBill, setNewBill] = useState({ familyId: '' });
  const [bills, setBills] = useState([]);
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState('');

  const form = useRef();
  const navigate = useNavigate();

  useEffect(() => {
    fetchBills();
  }, []);

  const fetchBills = () => {
    CustomerSupportService.getallbills()
      .then(response => {
        setBills(response);
      })
      .catch(error => {
        console.error('Error fetching bills : ', error);
      });
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewBill({ ...newBill, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage('');

    CustomerSupportService.addBill(newBill)
      .then(() => {
        setMessage('Bill added successfully');
        setNewBill({ familyId: '' });
        fetchBills();
      })
      .catch(error => {
        console.error('Error adding bills : ', error);
        setMessage('An error occurred while adding the bill');
      })
      .finally(() => {
        setLoading(false);
      });
  };

  return (
    <div className="container mt-5 pt-3">
      <h2 className="pb-3">ADD BILL</h2>
      <div className="card card-container mt-3 p-3">
        <form onSubmit={handleSubmit} ref={form}>
          <div className="mb-3">
            <label htmlFor="familyId" className="form-label">Family ID:</label>
            <input
              type="number"
              className="form-control"
              id="familyId"
              name="familyId"
              value={newBill.familyId}
              onChange={handleInputChange}
              required
            />
          </div>
          <button type="submit" className="btn btn-default" disabled={loading}>
            {loading ? 'Adding...' : 'ADD BILL'}
          </button>
        </form>
      </div>

      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th className="text-center">Bill ID</th>
            <th>Family Head</th>
            <th>Starting Period</th>
            <th>Ending Period</th>
            <th>Amount</th>
            <th>Paid</th>
            <th>Due Date</th>
          </tr>
        </thead>
        <tbody>
          {bills.map(bill => (
            <tr key={bill.billId}>
              <td className="text-center">{bill.billId}</td>
              <td>{bill.family.username}</td>
              <td>{bill.billingPeriodStart}</td>
              <td>{bill.billingPeriodEnd}</td>
              <td>{bill.amount}</td>
              <td>{bill.paid}</td>
              <td>{bill.dueDate}</td>
            </tr>
          ))}
        </tbody>
      </table>

    </div>
  );
};

export default Bills;