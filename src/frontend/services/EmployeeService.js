import axios from "axios";

const API_BASE_URL = "http://localhost:9090";

export function getAllEmployees() {
  return axios.get(`${API_BASE_URL}/api/employees`);
}

