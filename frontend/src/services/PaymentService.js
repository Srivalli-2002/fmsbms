import axios from "axios";
import authHeader from "./auth-header";
 
const BASE_URL = "http://localhost:8080/api/payments";
 
const addPayment = async (paymentData) => {
  try {
    const response = await axios.post(`${BASE_URL}/addpayment`, paymentData, {
      headers: {
        'Content-Type': 'application/json',
        ...authHeader(),
        'Access-Control-Allow-Origin': '*'
      }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};

const getPayment = async (paymentId) => {
  try {
    const response = await axios.get(`${BASE_URL}/getpayment`, {
      params: { paymentId },
      headers: {
        'Content-Type': 'application/json',
        ...authHeader(),
        'Access-Control-Allow-Origin': '*'
      }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};

const getAllPaymentsByUsername = async (username) => {
  try {
    const response = await axios.post(`${BASE_URL}/getallpaymentsbyusername`, username, {
      headers: {
        'Content-Type': 'application/json',
        ...authHeader(),
        'Access-Control-Allow-Origin': '*'
      }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};

const getBillByFamilyUsername = async (username) => {
  try {
    const response = await axios.post(`${BASE_URL}/getbillbyfamilyusername`, username, {
      headers: {
        'Content-Type': 'application/json',
        ...authHeader(),
        'Access-Control-Allow-Origin': '*'
      }
    });
    return response.data;
   
  } catch (err) {
    throw err;
  }
  
};

const PaymentService = {
    addPayment,
    getPayment,
    getAllPaymentsByUsername,
    getBillByFamilyUsername
};

export default PaymentService;