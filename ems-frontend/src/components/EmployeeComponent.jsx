import React from 'react'
import { useState } from 'react'
import { addEmployee } from '../services/EmployeeService';
import { useNavigate } from 'react-router';

const EmployeeComponent = () => {
    const[firstName,setFirstName]=useState('') ;
    const[lastName,setLastName]=useState('');
    const[email,setEmail ]=useState('');
    const navigator=useNavigate();
  
    function handlelastName(e){
        setLastName(e.target.value);
    }
    function handleEmail(e){
        setEmail(e.target.value);
    }
    function saveEmployee(e){
        e.preventDefault();
        const employee={firstName,lastName,email}
        //console.log("Payload sent:", employee);
       addEmployee(employee).then((response)=>
    {
        console.log(response.data);
        navigator('/employees')
    });
    }
  return (
    <div className=' max-w-screen flex justify-center items-center py-2 my-4 '>
<div className=' flex flex-col justify-start items-start border-2 border-neutral-200 px-10 rounded-2xl'>

    <h1 className='px-4 py-4'>Add employee</h1>
    <h5>First name:</h5>


    <input type='text' placeholder='Enter First name' name='firstName' value={firstName} onChange={(e)=>setFirstName(e.target.value)}
    className='border-1 border-neutral-200 px-20 py-1 rounded-full'/>


    <h5>Last name:</h5>
    <input type='text' placeholder='Enter Last name' name='lastName' value={lastName} onChange={handlelastName}
    className='border-1 border-neutral-200 px-20 py-1 rounded-full'/>
    <h5> email:</h5>
    <input type='email' placeholder='Enter E-mail'  name='email' value={email} onChange={handleEmail}
    
    className='border-1 border-neutral-200 px-20 py-1 rounded-full'/>



    <button className='bg-green-400 px-4 my-4 rounded-2xl shadow-2xl shadow-amber-200
     hover:bg-green-600 text-white font-bold' onClick={saveEmployee}>Submit</button>

</div>
</div>
  )
}

export default EmployeeComponent
