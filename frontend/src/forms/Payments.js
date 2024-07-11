import React, { useState, useEffect } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import AuthService from '../services/AuthService';
import PaymentService from '../services/PaymentService'; // Assuming you have a PaymentService for API calls
 
const Payment = () => {
  const [payments, setPayments] = useState([]);
  const [bills, setBills] = useState([]);
  const [paymentData, setPaymentData] = useState({
    username: '',
    billId: '',
    paymentMethod: '',
  });
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
 
  useEffect(() => {
    fetchBills();
    fetchPayments();
  }, []);
 
  const fetchPayments = () => {
    const username = AuthService.getCurrentUser().username;
    console.log(username);
    PaymentService.getAllPaymentsByUsername({ username })
      .then(response => {
        setPayments(response);
      })
      .catch(error => {
        console.error('Error fetching payments: ', error);
      });
  };

  const fetchBills = () => {
    const username = AuthService.getCurrentUser().username;
    console.log(username);
    PaymentService.getBillByFamilyUsername({ username })
      .then(response => {
        setBills(response);
      })
      .catch(error => {
        console.error('Error fetching bills: ', error);
      });
  };
 
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setPaymentData({ ...paymentData, [name]: value });
  };
 
  const handleSubmit = (e) => {
    e.preventDefault();
    setMessage('');
    setError('');
 
    PaymentService.addPayment(paymentData)
      .then(() => {
        setMessage('Payment added successfully');
        setPaymentData({ username: '', billId: '', paymentMethod: '' });
        fetchPayments();
      })
      .catch(error => {
        console.error('Error adding payment: ', error);
        setError('An error occurred while adding the payment');
      });
  };
 
  return (
    <div className="container mt-5 pt-5">
      <h3 className="mt-5">BILLS </h3>
      <table className="table table-striped table-bordered mt-3">
        <thead>
          <tr>
            <th>Bill ID</th>
            <th>Amount</th>
            <th>Start Period</th>
            <th>End Period</th>
            <th>Due Date</th>
            <th>Paid</th>
          </tr>
        </thead>
        <tbody>
          {bills.map(bill => (
            <tr key={bill.billId}>
              <td>{bill.billId}</td>
              <td>{bill.amount}</td>
              <td>{bill.billingPeriodStart}</td>
              <td>{bill.billingPeriodEnd}</td>
              <td>{bill.dueDate}</td>
              <td>{bill.paid.toString()}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <h2 className="mb-5 pt-3">PAYMENTS</h2>
      <div className="card card-container mt-3 p-3">
        <h3>ADD PAYMENT</h3>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="username" className="form-label">Username:</label>
            <input
              type="text"
              className="form-control"
              id="username"
              name="username"
              value={paymentData.username}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="billId" className="form-label">Bill ID:</label>
            <div className="input-group">
              <input
                type="text"
                className="form-control"
                id="billId"
                name="billId"
                value={paymentData.billId}
                onChange={handleInputChange}
                required
              />
            </div>
          </div>
          
          <div className="mb-3">
            <label htmlFor="paymentMethod" className="form-label">Payment Method:</label>
            <input
              type="text"
              className="form-control"
              id="paymentMethod"
              name="paymentMethod"
              value={paymentData.paymentMethod}
              onChange={handleInputChange}
              required
            />
          </div>
          <button type="submit" className="btn btn-default">ADD PAYMENT</button>
        </form>
        {message && <p className="mt-3 alert alert-success">{message}</p>}
        {error && <p className="mt-3 alert alert-danger">{error}</p>}
      </div>
 
      <h3 className="mt-5">PAYMENT HISTORY</h3>
      <table className="table table-striped table-bordered mt-3">
        <thead>
          <tr>
            <th>Payment ID</th>
            <th>Family ID</th>
            <th>Bill ID</th>
            <th>Amount</th>
            <th>Payment Date</th>
            <th>Payment Method</th>
          </tr>
        </thead>
        <tbody>
          {payments.map(payment => (
            <tr key={payment.paymentId}>
              <td>{payment.paymentId}</td>
              <td>{payment.family.username}</td>
              <td>{payment.bill.billId}</td>
              <td>{payment.amout}</td>
              <td>{payment.paymentDate}</td>
              <td>{payment.paymentMethod}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
 
export default Payment;