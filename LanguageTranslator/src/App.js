import React from 'react';
import { BrowserRouter, Route, Link, Routes } from 'react-router-dom';
import History from './components/History';
import { Home } from './components/Home';
import './App.css'
import './db.json'
import Translate from './components/Translate';
import Navigation from './components/Navigation';

function App() {


  return (
    <BrowserRouter>
      <div>
       <Navigation/>
      

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/translate" element={<Translate />} />


          <Route path="/history" element={<History />} />
        </Routes>
      </div>
    </BrowserRouter>
  )

}

export default App;