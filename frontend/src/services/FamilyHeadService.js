import axios from "axios";
import authHeader from "./auth-header";
 
const BASE_URL = "http://localhost:8080/api/family";
 
const buySim = async (familyData) => {
  try {
    const response = await axios.post(`${BASE_URL}/buysim`, familyData, {
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

const getMember = async (memberId) => {
  try {
    const response = await axios.post(`${BASE_URL}/getmember`, memberId, {
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

const getAllMembersByFamilyId = async (familyId) => {
  try {
    const response = await axios.get(`${BASE_URL}/getallmembersbyfamilyid`, familyId, {
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

const getAllMembersByUsername = async (username) => {
  try {
    const response = await axios.post(`${BASE_URL}/getallmembersbyusername`, username, {
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

const updateMember = async (memberData) => {
  try {
    const response = await axios.post(`${BASE_URL}/updatemember`, memberData, {
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

const addService = async (mobileServiceData) => {
  try {
    const response = await axios.get(`${BASE_URL}/addservice`, mobileServiceData, {
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
    const response = await axios.get(`${BASE_URL}/getbill`, billId, {
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

const FamilyHeadService = {
    
    buySim,
    getMember,
    getAllMembersByFamilyId,
    getAllMembersByUsername,
    updateMember,
    addService,
    getBill
};

export default FamilyHeadService;