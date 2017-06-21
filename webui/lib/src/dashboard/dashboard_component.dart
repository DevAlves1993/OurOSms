import 'package:angular2/angular2.dart';
import 'package:angular2/router.dart';
import 'package:angular_components/angular_components.dart';


@Component(selector: "form-dashboard"
  ,templateUrl: "dashboard_component.html"
  ,styleUrls: const ["dashboard_component.css"]
  ,directives: const [materialDirectives,COMMON_DIRECTIVES]
  ,providers: const [materialProviders])
class DashboardComponent {
  
}
