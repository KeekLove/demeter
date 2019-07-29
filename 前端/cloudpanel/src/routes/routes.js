import DashboardLayout from "@/pages/Layout/DashboardLayout.vue";

import Dashboard from "@/pages/Dashboard.vue";
import UserProfile from "@/pages/UserProfile.vue";
import TableList from "@/pages/TableList.vue";
import Typography from "@/pages/Typography.vue";
import Icons from "@/pages/Icons.vue";
import Login from "@/pages/Login.vue";
import Notifications from "@/pages/Notifications.vue";
import UpgradeToPRO from "@/pages/UpgradeToPRO.vue";

const routes = [
  {
    path: "/",
    component: DashboardLayout,
    redirect: "/dashboard",
    children: [
      {
        path: "dashboard",
        name: "Dashboard",
        component: Dashboard,
        meta: {
          requireToken:true
        }
      },
      {
        path: "user",
        name: "User Profile",
        component: UserProfile,
        meta: {
          requireToken:true
        }
      },
      {
        path: "table",
        name: "Table List",
        component: TableList,
        meta: {
          requireToken:true
        }
      },
      {
        path: "typography",
        name: "Typography",
        component: Typography,
        meta: {
          requireToken:true
        }
      },
      {
        path: "icons",
        name: "Icons",
        component: Icons,
        meta: {
          requireToken:true
        }
      },
      {
        path: "login",
        name: "Login",
        meta: {
          hideFooter: true,
          // requireToken:true
        },
        component: Login
      },
      {
        path: "notifications",
        name: "Notifications",
        component: Notifications,
        meta:{
          requireToken:true
        }
      },
      {
        path: "upgrade",
        name: "Upgrade to PRO",
        component: UpgradeToPRO,
        meta:{
          requireToken:true
        }
      }
    ]
  }
];

export default routes;
