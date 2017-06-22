import 'dart:html' as html;

import 'package:angular2/angular2.dart';
import 'package:angular_components/angular_components.dart';
import 'package:webui/src/model/auth_request.dart';
import 'package:webui/src/service/auth_service.dart';

@Component(selector: "form-login"
  ,templateUrl: "login_component.html"
  ,styleUrls: const ["login_component.css"]
  ,directives: const [materialDirectives,COMMON_DIRECTIVES]
  ,providers: const [materialProviders, AuthenticationService])
class LoginComponent implements OnInit{
  final AuthenticationService _authService;
  AuthenticationRequest auth;
  bool formIsNotValid = false;
  bool isCheck = false;

  LoginComponent(this._authService);

  void submit() {
    isCheck = true;
    _authService.login(auth)
      .then((b) => b ? _authService.redirectToViewDashboard() : formIsNotValid = true)
      .whenComplete(() {
        html.window.console.info("A authentication request is perform");
        isCheck = false;
      });
  }
  @override
  ngOnInit() {
    auth = new AuthenticationRequest.init();
  }
}
