import React from 'react'
import EmployeeService from '../services/EmployeeService';
import {useState, useEffect} from 'react';
import { Link } from 'react-router-dom';
import Navbar from './Navbar';

const ListEmployeeComponent = () => {

    const [employees, setEmployees] = useState([])

    useEffect(() => {

        getAllEmployees();
    }, [])

    const getAllEmployees = () => {
        EmployeeService.getEmployees().then((response) => {
            setEmployees(response.data)
            console.log(response.data);
        }).catch(error =>{
            console.log(error);
        })
    }

    const deleteEmployee = (employeeId) => {
       EmployeeService.deleteEmployee(employeeId).then((response) =>{
        getAllEmployees();

       }).catch(error =>{
           console.log(error);
       })
        
    }

    return (
        <>
       <Navbar/>
        <div className = "container">
            <h2 className = "text-center"> List Employees </h2>
            <Link to = "/add-employee" className = "btn btn-primary mb-2" > Add Employee </Link>
            <table className="table table-bordered table-striped">
                <thead>
                    <th> Emp Id </th>
                    <th> Emp First Name </th>
                    <th> Emp Last Name </th>
                    <th> Emp Email Id </th>
                    <th> Emp mobileNumber </th>
                    <th> Emp vehicleModel </th>
                    <th> Emp vehicleNumber </th>
                    <th> Actions </th>
                </thead>
                <tbody>
                    {
                        employees.map(
                            employee =>
                            <tr key = {employee.employeeId}> 
                                <td> {employee.employeeId} </td>
                                <td> {employee.firstName} </td>
                                <td>{employee.lastName}</td>
                                <td>{employee.email}</td>
                                <td>{employee.mobileNumber}</td>
                                <td>{employee.vehicleModel}</td>
                                <td>{employee.vehicleNumber}</td>
                                <td>
                                    <Link className="btn btn-info" to={`/edit-employee/${employee.employeeId}`} >Update</Link>
                                    <button className = "btn btn-danger" onClick = {() => deleteEmployee(employee.employeeId)}
                                    style = {{marginLeft:"10px"}}> Delete</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
        </>
    )
}

export default ListEmployeeComponent

