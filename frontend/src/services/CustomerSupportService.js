import axios from "axios";
import authHeader from "./auth-header";
 
const BASE_URL = "http://localhost:8080/api/billing";
 
const addBill = async (billData) => {
  try {
    const response = await axios.post(`${BASE_URL}/addbill`, billData, {
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

const getBill = async (billId) => {
  try {
    const response = await axios.post(`${BASE_URL}/getbill`, billId, {
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

const getallbills = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/getallbills`, {
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

const CustomerSupportService = {
  addBill,
  getBill,
  getallbills
};

export default CustomerSupportService;