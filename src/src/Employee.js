import React from "react";

const Employee =(props)  =>{
  <div>
    <p>
      <b>Name:</b>{props.firstName} {props.lastName}
    </p>
    <p><b>Email:</b>{props.everestEmailId}</p>
  </div>

}

export default Employee;