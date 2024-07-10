import axios from "axios";
import authHeader from "./auth-header";
 
const BASE_URL = "http://localhost:8080/api/billing";
 
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
    const response = await axios.get(`${BASE_URL}/getpayment`, paymentId, {
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

const getAllPaymentsByFamilyId = async (familyId) => {
  try {
    const response = await axios.post(`${BASE_URL}/getallpaymentsbyfamilyid`, familyId, {
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
    getAllPaymentsByFamilyId
};

export default PaymentService;