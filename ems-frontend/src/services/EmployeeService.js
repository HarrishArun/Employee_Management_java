import axios from 'axios';

const REST_API_BASE_URL='http://localhost:8080/api/employees';

export const listEmployees=()=>{
    return axios.get(REST_API_BASE_URL);
}

export const addEmployee=(employee)=>{
    return axios.post(REST_API_BASE_URL,employee);
}
export const getEmployee=(employeeID)=>axios.get(REST_API_BASE_URL+'/'+employeeID)

export const updateEmployee=(employeeID,employee)=>axios.put(REST_API_BASE_URL+'/'+employeeID,employee);

export const deleteEmployee=(employeeID)=>axios.delete(REST_API_BASE_URL+'/'+employeeID);