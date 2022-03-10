import axios from "axios";
import React, {useState,useEffect} from "react";
import * as api from "./services/EmployeeService";
function DataFetching(){
    const[employees,setEmployees]=useState([])

    useEffect(()=>{
        axios.get(api.fetchEmployees).then(
            res =>{
                console.log(res)
                setEmployees(res.data.data)
            }
        ).catch(err=>{
            console.log(err)
        })
    })
    return(
        <div>
<ul>
    {
        employees.map(emp =>
            <li key={emp.empId}>
                {emp.empId}
                </li>)
    }
</ul>
        </div>
    )
}

export default DataFetching;