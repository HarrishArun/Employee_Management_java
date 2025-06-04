import React from 'react'
import { useEffect } from 'react'
import { useState } from 'react'
import { listEmployees } from '../services/EmployeeService'

const Listofemployess = () => {
   const [employees,setEmployees]=useState([])
   useEffect(()=>{
listEmployees().then((response)=>{
    setEmployees(response.data);
}).catch(error=>console.error(error));
   },[])
  return (
    <div className='container'>
        <h1 className='text-center'>List of Employess</h1>
        <table className='table table-striped'>
            <thead>
                <tr>
                    <th>Employee id</th>
                    <th>FirstName</th>
                    <th>LastName</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                {
                    employees.map(employee=>
                        <tr key={employee.id}>
                            <td>{employee.id}</td>
                            <td>{employee.firstName}</td>
                            <td>{employee.lastName}</td>
                            <td>{employee.email}</td>

                        
                        </tr>
                    )
                }
            </tbody>

        </table>
    </div>
  )
}

export default Listofemployess