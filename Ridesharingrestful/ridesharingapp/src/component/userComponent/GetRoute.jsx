import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { NavLink } from 'react-router-dom';
import Skeleton from "react-loading-skeleton";
import Navbar from './Navbar';

export default function GetRoute() {

    const { id } = useParams();
    const [route, setRoute] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const getRoute = async () => {
            setLoading(true);
            const response = await fetch(`http://localhost:8877/user/route/${id}`);
            setRoute(await response.json());
            setLoading(false);
        }
        getRoute();
    }, []);

    const Loading = () => {
        return (
            <>
                <div className="container">
                    <Skeleton height={400}/>
                </div>
            </>
        )
    }
    const ShowRoute = () => {
        return (
            <>
                <div className="container">
                    <div class="card text-center">
                        <div class="card-header">
                            <h4>Route</h4>
                        </div>
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
    }

    return (
        <>
        <Navbar/>
        <div>
            <div className="container py-5">
                <div className="row py-4">
                    {loading ? <Loading /> : <ShowRoute />}
                </div>
            </div>
        </div>
        </>
    )
}
