// Copyright (c) 2017, Christian Amani. All rights reserved. Use of this source code
// is governed by a BSD-style license that can be found in the LICENSE file.

import 'package:angular2/core.dart';
import 'package:angular2/router.dart';
import 'package:webui/pages/404/page_not_found.dart';
import 'package:webui/pages/500/page_internal_error.dart';
import 'package:webui/pages/dashboard/dashboard_component.dart';
import 'package:webui/pages/login/login_component.dart';

@Component(selector: 'my-app',
  templateUrl: "app.html",
  styleUrls: const ["app.css","asset/w3.css"]
  ,directives: const [ROUTER_DIRECTIVES]
  ,providers: const [ROUTER_PROVIDERS])
@RouteConfig(const [
  const Redirect(path: "/",redirectTo: const [DashboardComponent]),
  const Redirect(path: "/index.html",redirectTo: const [DashboardComponent]),
  const Route(path:"/home",name:"Home",component:DashboardComponent),
  const Route(path: "/login",name: "Login",component: LoginComponent,),
  const Route(path: "error/**",name: "Error",component: PageInternalError),
  const Route(path: "/**",name: "Not Found",component: PageNotFound)
])
class AppComponent {
}
