import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Listofemployess from './components/Listofemployess'
import HeaderComponenet from './components/HeaderComponent'
import {BrowserRouter,Routes,Route} from'react-router-dom'

function App() {

  return (
    <>
    <BrowserRouter>
     <HeaderComponenet/>
     <Routes>
      <Route path='/' element={<Listofemployess/>}></Route>
      <Route path='/employees' element={<Listofemployess/>}></Route>
     </Routes>
     
      </BrowserRouter>
     
      

    </>
  )
}

export default App
