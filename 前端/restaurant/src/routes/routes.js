import DashboardLayout from "@/pages/Layout/DashboardLayout.vue";

import Dashboard from "@/pages/RMenu.vue";
import UserProfile from "@/pages/UserProfile.vue";
import TableList from "@/pages/Instance.vue";
import Typography from "@/pages/Typography.vue";
import Icons from "@/pages/Icons.vue";
import Maps from "@/pages/Maps.vue";
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
        name: "本店菜单",
        component: Dashboard
      },
      {
        path: "user",
        name: "用户信息",
        component: UserProfile
      },
      {
        path: "table",
        name: "餐厅信息",
        component: TableList
      },
      {
        path: "typography",
        name: "历史订单",
        component: Typography
      },
      {
        path: "icons",
        name: "Icon",
        component: Icons
      },
      {
        path: "maps",
        name: "套餐",
        meta: {
          hideFooter: true
        },
        component: Maps
      },
      {
        path: "notifications",
        name: "授权列表",
        component: Notifications
      },
      {
        path: "upgrade",
        name: "Upgrade to PRO",
        component: UpgradeToPRO
      }
    ]
  }
];

export default routes;
