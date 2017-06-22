import 'dart:html' as html;

import 'package:angular2/angular2.dart';
import 'package:angular2/router.dart';
import 'package:angular_components/angular_components.dart';
import 'package:webui/src/sidebar/sidebar_component.dart';


@Component(selector: "form-dashboard"
  ,templateUrl: "dashboard_component.html"
  ,styleUrls: const ["dashboard_component.css"]
  ,directives: const [materialDirectives,COMMON_DIRECTIVES,SidebarComponent]
  ,providers: const [materialProviders])
@CanActivate(authentication)
class DashboardComponent implements OnInit {

  bool isOpen = false;

  DashboardComponent();

  void openOrClose() {
    isOpen = !isOpen;
   if(isOpen) {
    html.Element sideBar = html.document.getElementById("mySidebar");
    sideBar.style.display = "block";
    html.Element homeElement = html.document.getElementById("home");
    homeElement.style.marginLeft = "20%";
   }
   else {
    html.Element sideBar = html.document.getElementById("mySidebar");
    sideBar.style.display = "none";
    html.Element homeElement = html.document.getElementById("home");
    homeElement.style.marginLeft = "0%";
   }
  }

  @override
  ngOnInit() {
    openOrClose();
  }
}

/**
 * Global Injector for the project
 */
Injector myInjector;
/**
 * Function for the Authentication
 */
bool authentication(ComponentInstruction next, ComponentInstruction prev) {
  String value = html.window.localStorage['isAuthenticated'];
  html.window.console.info("Method isAuthenticate is perform");
  if(value  == null) {
    Router router = myInjector.get(Router);
    html.window.console.info("Redirect To Login Componenent");
    router.navigate(const ["/Login"]);
    return false;
  }
  html.window.console.info("Component is Activate");
  return true;
}

