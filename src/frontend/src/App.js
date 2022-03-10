
import React, {useState, useEffect} from "react";
import * as employeeService from "./services/EmployeeService";
import 'bootstrap/dist/css/bootstrap.min.css';
const App =()=>{
  const[employees,setEmployees]=useState([])
  const[empId,setEmpId]=useState("");

  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
    const [everestEmailId, setEverestEmailId] = useState("");
    const [password, setPassword] = useState("");
    const[personalEmailId,setPersonalEmailId]=useState("")

     const [isUpdate, setIsUpdate] = useState(false)
const editEmployee =(id) =>{
employeeService.getEmployeeById(id).then(response => {
setEmpId(response.data.empId)
 setFirstName(response.data.firstName);
 setLastName(response.data.lastName);
      setEverestEmailId(response.data.everestEmailId);
      setPassword(response.data.password);
      setPersonalEmailId(response.data.personalEmailId);
      setIsUpdate(true)
}
)
}

  const getAllEmployees = () => {
    employeeService.getAllEmployees().then(response => {
        console.log("fetched employees successfully", response)
        setEmployees(response.data);
    }).catch(e => console.log("error", e));
}
const handleSubmit = e => {
  e.preventDefault();
  if(!firstName || !everestEmailId || !password) {
      alert("Enter all fields");
      return;
  }
  const employee = {
      firstName,
      lastName,
      everestEmailId,
      password,
       personalEmailId,


  };

employeeService.createEmployee(employee).then(response => {
      console.log("employee saved successfully", response)
      setFirstName("");
      setLastName("");
                setEverestEmailId("");
                setPassword("");
                setPersonalEmailId("");
                console.log(empId)

  }).catch(e => console.log("error", e));
};
const updateEmployee = (e) => {
 e.preventDefault();
		var employee = {firstName: firstName,lastName:lastName, everestEmailId: everestEmailId, password: password,personalEmailId: personalEmailId};
	console.log(employee)
	employeeService.updateEmployee(empId,employee).then(response =>{
	 console.log("employee saved successfully", response)
           setFirstName("");
           setLastName("")
           setEverestEmailId("");
           setPassword("");
           setPersonalEmailId("");

           getAllEmployees();
	}).catch(e => console.log("error", e));
	};
const deleteEmployee = (id) => {
       employeeService.deleteEmployee(id).then(response => {
            console.log("employee deleted successfully", response)
            getAllEmployees();
        }).catch(e => console.log("error", e));
    }
    useEffect(()=>{
        getAllEmployees();
    }, []);
return (
  <div className="container">
      <h1>Employee Portal</h1>
      <hr/>
      <div id={"addEmployeeForm"}>
          <h2>Add New Employee</h2>
          <form onSubmit={e => handleSubmit(e)} className="row justify-content-center">
              <div className="form-group col-md-5">
                  <label htmlFor="firstName">FirstName</label>
                  <input
                      id="firstName"
                      placeholder={"Enter the firstName"}
                      className="form-control col-md-12"
                      value={firstName}
                      onChange={e => setFirstName(e.target.value)}
                  />
                  </div>
                  <div className="form-group col-md-5">
                  <label htmlFor="lastName">LastName</label>
                                          <input
                                              id="lastName"
                                              placeholder={"Enter the lastName"}
                                              className="form-control col-md-12"
                                              value={lastName}
                                              onChange={e => setLastName(e.target.value)}
                                          />
              </div>
              <div className="form-group col-md-5">
                  <label htmlFor="email">Everest Email</label>
                  <input
                      id="email"
                      placeholder={"Enter the email"}
                      className="form-control col-md-12"
                      type="text"
                      value={everestEmailId}
                      onChange={e => setEverestEmailId(e.target.value)}
                  />
              </div>
              <div className="form-group col-md-5">
                  <label htmlFor="password">Password</label>
                  <input
                      id="password"
                       placeholder={"Enter the password"}
                      className="form-control col-md-12"
                      type="password"
                      value={password}
                      onChange={e => setPassword(e.target.value)}
                  />
              </div>
              <div className="form-group col-md-10">
                  <label htmlFor="email">Personal Email</label>
                  <input
                      id="email"
                      placeholder={"Enter the email"}
                      className="form-control col-md-12"
                      type="text"
                      value={personalEmailId}
                      onChange={e => setPersonalEmailId(e.target.value)}
                  />
             </div>
              <div className="form-group col-md-10">
              {
              isUpdate === true ? <button type="submit" className="btn btn-primary" onClick={updateEmployee}>Update</button> :  <button type="submit" className="btn btn-primary">Save</button>
              }

              </div>
          </form>
      </div>
      <div id={"employeeList"}>
          <table className={"table"}>
              <thead>
              <tr>
                  <td>Id</td>
                  <td>Name</td>
                  <td>Email</td>
                  <td>Edit</td>
                  <td>Delete</td>
              </tr>
              </thead>
              <tbody>
              {employees.map(emp => {
                  return (
                      <tr key={emp.id}>
                          <td>{emp.empId}</td>
                          <td>{emp.firstName} {emp.lastName}</td>
                          <td>{emp.everestEmailId}</td>

                            <td><button className={"btn btn-primary"} onClick={()=> editEmployee(emp.empId)}>Edit</button></td>
                      <td><button className={"btn btn-danger"} onClick={()=> deleteEmployee(emp.empId)}>Delete</button></td>

                      </tr>
                  );
              })}
              </tbody>
          </table>

      </div>
  </div>
);
}
export default App;
