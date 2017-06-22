// Copyright (c) 2017, Christian Amani. All rights reserved. Use of this source code
// is governed by a BSD-style license that can be found in the LICENSE file.

import 'package:angular2/angular2.dart';
import 'package:angular_components/angular_components.dart';
import 'package:angular2/platform/common.dart';
import 'package:angular2/router.dart';
import 'package:webui/src/dashboard/dashboard_component.dart';
import 'package:webui/src/login/login_component.dart';
import 'package:webui/src/service/local_storage_service.dart';


// AngularDart info: https://webdev.dartlang.org/angular
// Components info: https://webdev.dartlang.org/components

@Component(
  selector: 'my-app',
  styleUrls: const ['app_component.css'],
  templateUrl: 'app_component.html',
  directives: const [materialDirectives,COMMON_DIRECTIVES,ROUTER_DIRECTIVES,LoginComponent,DashboardComponent],
  providers: const [ROUTER_PROVIDERS,LocalStorageService,const Provider(LocationStrategy, useClass: HashLocationStrategy)]
)
@RouteConfig(const [
  const Route(name: "Dashboard",component: DashboardComponent,path: "/home",useAsDefault: true),
  const Route(name: "Login",component: LoginComponent,path: "/login")
  ])
class AppComponent {
  // Nothing here yet. All logic is in TodoListComponent.
}
