import axios from 'axios';

const ROUTE_API_BASE_URL = "http://localhost:8877/user/route";

class RouteService {

    getEmployees(){
        return axios.get(ROUTE_API_BASE_URL);
    }

    createEmployee(route){
        return axios.post(ROUTE_API_BASE_URL, route);
    }

    getEmployeeById(routeId){
        return axios.get(ROUTE_API_BASE_URL + '/' + routeId);
    }

    updateEmployee(route, routeId){
        return axios.put(ROUTE_API_BASE_URL + '/' + routeId, route);
    }

    deleteEmployee(employeeId){
        return axios.delete(ROUTE_API_BASE_URL + '/' + routeId);
    }

    searchEmployee(keyword){
        return axios.get(ROUTE_API_BASE_URL + '/searchRoute/' + keyword);
    }
}

export default new RouteService()