import React, { useState, useRef, useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import SystemAdminService from '../services/SystemAdminService';
import 'bootstrap/dist/css/bootstrap.min.css';
import './MobilePlans.css';

const MobilePlans = () => {
  const [newPlan, setNewPlan] = useState({ planType: '', totalMembers: '', amount: '' });
  const [plans, setPlans] = useState([]);
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState('');

  const form = useRef();
  const navigate = useNavigate();

  useEffect(() => {
    fetchPlans();
  }, []);

  const fetchPlans = () => {
    SystemAdminService.viewPlans()
      .then(response => {
        setPlans(response);
      })
      .catch(error => {
        console.error('Error fetching mobile plan : ', error);
      });
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewPlan({ ...newPlan, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage('');

    SystemAdminService.addPlan(newPlan)
      .then(() => {
        setMessage('Plan added successfully');
        setNewPlan({ planType: '', totalMembers: '', amount: '' });
        fetchPlans();
      })
      .catch(error => {
        console.error('Error adding mobile plan : ', error);
        setMessage('An error occurred while adding the plan');
      })
      .finally(() => {
        setLoading(false);
      });
  };

  return (
    <div className="container mt-5 pt-3">
      <h2 className="pb-3">ADD MOBILE PLANS</h2>
      <div className="card card-container mt-3 p-3">
        <form onSubmit={handleSubmit} ref={form}>
          <div className="mb-3">
            <label htmlFor="planType" className="form-label">Plan Type:</label>
            <input
              type="text"
              className="form-control"
              id="planType"
              name="planType"
              value={newPlan.planType}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="totalMembers" className="form-label">Total Members:</label>
            <input
              type="number"
              className="form-control"
              id="totalMembers"
              name="totalMembers"
              value={newPlan.totalMembers}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="amount" className="form-label">Amount:</label>
            <input
              type="number"
              className="form-control"
              id="amount"
              name="amount"
              value={newPlan.amount}
              onChange={handleInputChange}
              required
            />
          </div>
          <button type="submit" className="btn btn-default" disabled={loading}>
            {loading ? 'Adding...' : 'ADD MOBILE PLAN'}
          </button>
        </form>
      </div>

      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th className="text-center">Plan Type</th>
            <th>Total Members</th>
            <th>Amount</th>
          </tr>
        </thead>
        <tbody>
          {plans.map(plan => (
            <tr key={plan.planType}>
              <td className="text-center">{plan.planType}</td>
              <td>{plan.totalMembers}</td>
              <td>{plan.amount}</td>
            </tr>
          ))}
        </tbody>
      </table>

    </div>
  );
};

export default MobilePlans;