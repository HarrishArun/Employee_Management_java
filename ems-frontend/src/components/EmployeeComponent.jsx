import React, { useState } from 'react';
import { addEmployee, getEmployee, updateEmployee } from '../services/EmployeeService';
import { useNavigate, useParams } from 'react-router';
import { useEffect } from 'react';

const EmployeeComponent = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const navigate = useNavigate();
  const{id}=useParams();
  

  const [errors, setErrors] = useState({
    firstName: '',
    lastName: '',
    email: '',
  });
   useEffect(
    ()=>{
        if(id){
            getEmployee(id).then((response)=>{
                setFirstName(response.data.firstName);
                setLastName(response.data.lastName);
                setEmail(response.data.email);

            }).catch(error=>{
                console.error(error);
            })
        }
    },[id]
   );

  function validateform() {
    let valid = true;
    const errorsCopy = { ...errors };

    if (firstName.trim()) {
      errorsCopy.firstName = '';
    } else {
      errorsCopy.firstName = 'First name is required';
      valid = false;
    }

    if (lastName.trim()) {
      errorsCopy.lastName = '';
    } else {
      errorsCopy.lastName = 'Last name is required';
      valid = false;
    }

    if (email.trim()) {
      errorsCopy.email = '';
    } else {
      errorsCopy.email = 'Email is required';
      valid = false;
    }

    setErrors(errorsCopy); // Update the errors in state
    return valid;
  }

  function handlelastName(e) {
    setLastName(e.target.value);
  }

  function handleEmail(e) {
    setEmail(e.target.value);
  }

  function saveorupdateEmployee(e) {
    e.preventDefault();

    if (validateform()) { 
        
         const employee = { firstName, lastName, email };
   
   
        if(id){
            updateEmployee(id,employee).then((response)=>{
                console.log(response.data);
                navigate('/employees')
            }).catch(error=>{
                console.error(error)
                alert('Something went wrong!');
            })
        }
        else{  addEmployee(employee)
            .then((response) => {
              console.log(response.data);
              navigate('/employees');
            })
            .catch((error) => {
              console.error('Submission failed:', error);
              alert('Something went wrong!');
            });}
      
    }
  }

  function pageTitle(){
  
    if(id){
        return   <h1 className='text-xl font-semibold mb-4'>Update Employee</h1>
    }
    else{
        return <h1 className='text-xl font-semibold mb-4'>Add Employee</h1>
    }
  }
  return (
    <div className='max-w-screen flex justify-center items-center py-2 my-4'>
      <div className='flex flex-col justify-start items-start border-2 border-neutral-200 px-10 py-6 rounded-2xl'>

        {
            pageTitle()
        }

        <label>First name:</label>
        <input
          type='text'
          placeholder='Enter First name'
          name='firstName'
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
          className='border border-neutral-300 px-4 py-1 rounded-full mb-1'
        />
        {errors.firstName && <p className='text-red-500 text-sm'>{errors.firstName}</p>}

        <label className='mt-3'>Last name:</label>
        <input
          type='text'
          placeholder='Enter Last name'
          name='lastName'
          value={lastName}
          onChange={handlelastName}
          className='border border-neutral-300 px-4 py-1 rounded-full mb-1'
        />
        {errors.lastName && <p className='text-red-500 text-sm'>{errors.lastName}</p>}

        <label className='mt-3'>Email:</label>
        <input
          type='email'
          placeholder='Enter Email'
          name='email'
          value={email}
          onChange={handleEmail}
          className='border border-neutral-300 px-4 py-1 rounded-full mb-1'
        />
        {errors.email && <p className='text-red-500 text-sm'>{errors.email}</p>}

        <button
          className='bg-green-500 hover:bg-green-600 text-white font-bold py-2 px-6 rounded-2xl mt-4'
          onClick={saveorupdateEmployee}
        >
          Submit
        </button>
      </div>
    </div>
  );
};

export default EmployeeComponent;
