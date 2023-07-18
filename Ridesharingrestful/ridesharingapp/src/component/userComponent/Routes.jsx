import React, { useState, useEffect } from 'react';
import { NavLink } from 'react-router-dom';
import Skeleton from "react-loading-skeleton";
import Navbar from './Navbar';

export default function Routes() {

    const [data, setData] = useState([])
    const [filter, setFilter] = useState(data)
    const [loading, setLoading] = useState(false)
    let componentMounted = true;

    useEffect(() => {
        const getRoutes = async () => {
            setLoading(true);
            const response = await fetch("http://localhost:8877/user/route");
            if (componentMounted) {
                setData(await response.clone().json());
                setFilter(await response.json());
                setLoading(false);
                console.log(filter);
            }

            return () => {
                componentMounted = false;
            }
        }

        getRoutes();
    }, [])

    const Loading = () => {
        return (
            <>
                <div className="col-md-3">
                    <Skeleton height={350}/>
                </div>
            </>
        );
    };

    const ShowRoutes = () => {
        return (
            <>
            
                <div className="buttons d-flex justify-content-center mb-5 pb-5">
                    <button className="btn btn-outline-dark me-2" onClick={() =>setFilter(data)}>All</button>
                </div>
                {filter.map((route) => {
                    return (
                        <>
                            <div className="col-md-3 mb-4">
                                <div class="card h-100 text-center p-4" key={route.routeId}>
                                    <div class="card-body">
                                        <h4 className="card-title">{route.startPoint} To {route.endPoint}</h4>
                                        
                                        <p className="card-text lead fw-bold">
                                            Date: {route.date}
                                        </p>
                                        <p className="card-text lead fw-bold">
                                            Time: {route.time}
                                        </p>
                                        <p className="card-text lead fw-bold">
                                            Seats: {route.seats}
                                        </p>
                                        <p className="card-text lead fw-bold">
                                            Distance: {route.distance}
                                        </p>
                                        <NavLink to={`/routes/${route.routeId}`} className="btn btn-primary">
                                            Book Now
                                            </NavLink>
                                    </div>
                                </div>
                            </div>
                        </>
                    )
                })}
            </>
        );
    };

    return (
        <>
        <Navbar/>
        <div>
            <div className="container my-5 py-5">
                <div className="row">
                    <div className="col-12 mb-5">
                        <h1 className="display-6 fw-bolder
                    text-center">Routes</h1>
                        <hr />
                    </div>
                    <div className="row justify-content-center">
                        {loading ? <Loading /> : <ShowRoutes />}
                    </div>
                </div>
            </div>
        </div>
        </>
    )
}
