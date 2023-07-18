import './App.css';
import Home from './common/Home';
import adminDashboard from './component/adminComponent/adminHome';
import Dashboard from './component/userComponent/userHome';
import driverComponent from './component/driverComponent/driverHome';
import { Routes, Route } from 'react-router-dom';
import AllRoutes from './component/userComponent/Routes';
import GetRoutes from './component/userComponent/GetRoute';
import ListEmployeeComponent from './common/ListEmployeeComponent';
import AddEmployeeComponent from './common/AddEmployeeComponent';

function App() {
  return (
    <>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/admindashboard" element={<adminDashboard />} />
        <Route path="/driverdashboard" element={<driverComponent />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/routes" element={<AllRoutes />} />
        <Route path="/routes/:id" element={<GetRoutes />} />
        <Route path="/employees" element={<ListEmployeeComponent />} />
        <Route path="/add-employee" element={<AddEmployeeComponent />} />
        <Route path="/edit-employee/:id" element={<AddEmployeeComponent />} />
      </Routes>
    </>
  );
}

export default App;
