import React from 'react';
import { NavLink } from 'react-router-dom';

export default function Navbar() {
    return (
        <div>
            <nav className="navbar navbar-expand-lg bg-white py-3 shadow-sm">
                <div className="container">
                    <NavLink className="navbar-brand fw-bold fs-4" to="/driverdashboard">Car Pool</NavLink>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav mx-auto mb-2 mb-lg-0">
                            <li className="nav-item">
                                <NavLink className="nav-link active" aria-current="page" to="/">Home</NavLink>
                            </li>
                            <li className="nav-item">
                                <NavLink className="nav-link" to="/routes">Routes</NavLink>
                            </li>
                            <li className="nav-item">
                                <NavLink className="nav-link" to="/myRoutes">MyRoutes</NavLink>
                            </li>

                        </ul>
                        <div className="buttons">
                            <NavLink to="/logout" className="btn btn-outline-dark">
                                <i className="fa fa-sign-in me-1"></i> Logout</NavLink>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
    );
}
