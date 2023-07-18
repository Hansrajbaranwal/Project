import React from 'react'
import Navbar from './Navbar'

export default function userHome() {
    return (
        <>
        <Navbar />
        <div className='home'>
            <div className="card text-bg-dark border-0">
                <img src="../assets/carpool.png" className="card-img" alt="Background" 
                height="550px"/>
                <div className="card-img-overlay">
                    <div className='container'>
                    <h5 style = {{color:"blue"}} className="card-title display-3 fw-bolder mb-0">Pick your ride here</h5>
                    
                </div>
                </div>
            </div>
        </div>
        </>
    )
}
