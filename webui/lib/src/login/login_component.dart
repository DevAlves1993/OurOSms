import 'package:angular2/angular2.dart';
import 'package:angular_components/angular_components.dart';

@Component(selector: "form-login"
  ,templateUrl: "login_component.html"
  ,styleUrls: const ["login_component.css"]
  ,directives: const [materialDirectives,COMMON_DIRECTIVES]
  ,providers: const [materialProviders])
class LoginComponent {

  void login() {
    // TODO : implement later
  }
}
