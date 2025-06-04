import React from 'react'
import { useEffect } from 'react'
import { useState } from 'react'
import { deleteEmployee, listEmployees } from '../services/EmployeeService'
import { useNavigate } from 'react-router'

const Listofemployess = () => {

const navigator=useNavigate();

   const [employees,setEmployees]=useState([])
   useEffect(()=>{
getallemployee();
   },[])
   function getallemployee(){
    listEmployees().then((response)=>{
        setEmployees(response.data);
    }).catch(error=>console.error(error));
   }


   function addEmployee(){
    navigator('/addemployee')
   }

   function updateEmployee(id){
    navigator(`/editemployee/${id}`)
   }

   function removeEmployee(id){
    deleteEmployee(id).then((response)=>{

    }).catch(error=>{
        console.error(error);
    })
    getallemployee();
   }
  return (
    <div className='container'>
        <h1 className='text-center'>List of Employess</h1>
        <button className='bg-blue-500 hover:bg-blue-700 text-white  py-2 px-4 rounded-full font-bold border-amber-200
         shadow-xl'
         onClick={addEmployee}>
            
            Add Employee</button>
        <table className='table table-striped'>
            <thead>
                <tr>
                    <th>Employee id</th>
                    <th>FirstName</th>
                    <th>LastName</th>
                    <th>Email</th>
                    <th>Actions</th>
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
                            <td>
                                <button className='btn btn-info'
                                onClick={()=>updateEmployee(employee.id)}>Update</button>
                                 <button className='btn btn-danger mx-2'
                                onClick={()=>removeEmployee(employee.id)}>Delete</button>
                            </td>

                        
                        </tr>
                    )
                }
            </tbody>

        </table>
    </div>
  )
}

export default Listofemployess