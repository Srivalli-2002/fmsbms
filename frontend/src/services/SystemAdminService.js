import axios from "axios";
import authHeader from "../services/auth-header";
 
const BASE_URL = "http://localhost:8080/api/admin";
 
const updateRole = async (userData) => {
    try {
      const response = await axios.post(`${BASE_URL}/updaterole`, userData, {
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
  
  const viewUsers = async () => {
    try {
      const response = await axios.get(`${BASE_URL}/getallusers`, {
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

const allocatePhoneNumber = async (username,phoneNumber) => {
  try {
    const response = await axios.post(`${BASE_URL}/allocatephonenumber`, username, phoneNumber, {
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

const addPlan = async (planData) => {
  try {
    const response = await axios.post(`${BASE_URL}/addplan`, planData, {
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

const viewPlans = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/getallplans`, {
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



const SystemAdminService = {
    updateRole,
    viewUsers,
    allocatePhoneNumber,
    addPlan,
    viewPlans
};

export default SystemAdminService;