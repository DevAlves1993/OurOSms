import 'dart:html' as html;

import 'package:angular2/angular2.dart';
import 'package:angular2/router.dart';
import 'package:angular_components/angular_components.dart';

@Component(selector: "form-sidebar"
  ,templateUrl: "sidebar_component.html"
  ,styleUrls: const ["sidebar_component.css"]
  ,directives: const [materialDirectives,ROUTER_DIRECTIVES]
  ,providers: const [materialProviders,ROUTER_PROVIDERS])
class SidebarComponent{
  
}
