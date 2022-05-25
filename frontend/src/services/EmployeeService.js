import axios from "axios";

const API_BASE_URL =  process.env.REACT_APP_BASE_URL
console.log(process.env.REACT_APP_BASE_URL)
export function getAllEmployees() {
  return axios.get(`${API_BASE_URL}/api/employees`);
}

export function createEmployee(employee) {
  return axios.post(`${API_BASE_URL}/api/employees`, employee);
}
export function getEmployeeById(id) {
  return axios.get(`${API_BASE_URL}/api/employees/${id}`);
}
export function getEmployeeByFirstNameOrLastName(empName) {
  return axios.get(`${API_BASE_URL}/api/employees/search?query=${empName}`);
}

export function updateEmployee(id, employee) {
  return axios.put(`${API_BASE_URL}/api/employees/${id}`, employee);
}
export function deleteEmployee(id) {
  return axios.delete(`${API_BASE_URL}/api/employees/${id}`);
}
