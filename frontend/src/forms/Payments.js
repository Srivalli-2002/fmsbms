import PaymentService from '../services/PaymentService';
import { useNavigate, Link } from 'react-router-dom';
import { useState } from 'react';
import { useRef, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
 
const Payments = () => {
  const [newPayment, setNewPayment] = useState({
    billId: '',
    username: '',
    paymentMethod: ''
  });
  const [payments, setPayments] = useState([]);
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState('');
  const [selectedPayment, setSelectedPayment] = useState(null);
 
  const form = useRef();
  const navigate = useNavigate();
 
  useEffect(() => {
    fetchPayments();
  }, []);
 
  const fetchPayments = (familyId) => {
    PaymentService.getAllPaymentsByFamilyId(familyId)
      .then(response => {
        setPayments(response);
      })
      .catch(error => {
        console.error('Error fetching payments:', error);
      });
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewPayment({ ...newPayment, [name]: value });
  };
 
  const handleSubmit = (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage('');
    PaymentService.addPayment(newPayment)
      .then(() => {
        alert('Payment added successfully');
        setNewPayment({
            billId: '',
            username: '',
            paymentMethod: ''
        });
        fetchPayments();
      })
      .catch(error => {
        console.error('Error adding payment:', error);
        alert('An error occurred while adding payment');
      })
      .finally(() => {
        setLoading(false);
      });
  };
 
  const fetchPayment = async (paymentId) => {
    try {
      const payment = await PaymentService.getPayment(paymentId);
      setSelectedPayment(payment); // Set the selected device
    } catch (error) {
      console.error('Error fetching customer by ID:', error);
    }
  };
 
  return (
    <div className="container">
      <h2> ADD PAYMENT </h2>
      <div className="card card-container">
        <form onSubmit={handleSubmit} ref={form}>
          {console.log("dfhsfg",newPayment)}
          <div className="mb-3">
            <label htmlFor="billId" className="form-label">Bill ID : </label>
            <input type="number" className="form-control" id="billId" name="billId" value={newPayment.billId} onChange={handleInputChange} required />
          </div>
          <div className="mb-3">
            <label htmlFor="username" className="form-label">Family ID : </label>
            <input type="text" className="form-control" id="username" name="username" value={newPayment?.family?.username} onChange={handleInputChange} required />
          </div>
          <div className="mb-3">
            <label htmlFor="paymentMethod" className="form-label">Payment Method : </label>
            <input type="text" className="form-control" id="paymentMethod" name="paymentMethod" value={newPayment.paymentMethod} onChange={handleInputChange} required />
          </div>
          
          <button type="submit" className="btn btn-default" disabled={loading}>ADD PAYMENT</button>
        </form>
      </div>
 
      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th>Payment ID</th>
            <th>Amount</th>
            <th>Payment Date</th>
            <th>Payment Method</th>
            <th>Bill ID</th>
            <th>Family ID</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {payments.map(payment => (
            <tr key={payment.paymentId}>
              <td>{payment.paymentId}</td>
              <td>{payment.amout}</td>
              <td>{payment.paymentDate}</td>
              <td>{payment.billId}</td>
              <td>{payment.family.familyId}</td>
              <td>
              <button className="btn btn-default" onClick={() => fetchPayment(payment.paymentId)}>View</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>


      
      {selectedPayment &&(
      <div>
      <h2> PAYMENT DETAILS</h2>
      <table className="table mt-4 table-striped table-bordered">
        <thead>
          <tr>
            <th>Payment Form</th>
            <th>Payment Details</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>Amount</td>
            <td>{selectedPayment.name}</td>
          </tr>
          <tr>
            <td>Payment Date</td>
            <td>{selectedPayment.paymentDate}</td>
          </tr>
          <tr>
            <td>Payment Method</td>
            <td>{selectedPayment.paymentMethod}</td>
          </tr>
          <tr>
            <td>Bill ID</td>
            <td>{selectedPayment.bill.billId}</td>
          </tr>
          <tr>
            <td>Family ID</td>
            <td>{selectedPayment.family.familyId}</td>
          </tr>
        </tbody>
      </table>
      </div>
     )}
    </div>
  );
};
 
export default Payments;