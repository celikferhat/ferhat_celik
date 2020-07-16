
// @material-ui/icons
import Dashboard from "@material-ui/icons/Dashboard";


// core components/views for Admin layout
import DashboardPage from "views/Dashboard/Custom_dashboard.js";



const dashboardRoutes = [
  {
    path: "/dashboard",
    name: "Dashboard",
    rtlName: "لوحة القيادة",
    icon: Dashboard,
    component: DashboardPage,
    layout: "/admin"
  }
 
  
 

];

export default dashboardRoutes;
