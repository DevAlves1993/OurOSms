import 'dart:convert';
import 'package:angular2/angular2.dart';
import 'package:angular2/router.dart';
import 'package:angular_components/src/components/material_input/material_input.dart';

import 'package:http/http.dart';
import 'package:logging/logging.dart';
import 'package:webui/model/user.dart';
import 'package:webui/service/authentication/login_service.dart';

@Component(
    selector: "login-component",
    templateUrl: "login.html",
    styleUrls: const ["login.css","../../asset/w3.css"],
    providers: const [LoginService],
    directives: const [MaterialInputComponent]
)
class LoginComponent {
    final Logger _LOG =  new Logger("LoginComponent");
    Router _router;
    LoginService _service; 
    AuthenticationBody body;
    String errorMsg = "";
    
    LoginComponent(this._router,this._service);

    void login()
    {
        _service.submitAuthentication(body)
            .then((response) => _manageResponse(response));
    }

    void _manageResponse(Response response)
    {
        if(response.statusCode == 201)
        {
            dynamic value = JSON.decode(response.body)["data"];
            UserInfo userInfo = new UserInfo.fromJson(value);
            _service.saveUserInfo(userInfo);
            _LOG.info("User Info was save with success");
        }
        else if(response.statusCode == 404)
        {
            errorMsg = "Veiller Insérer des données dans les champs du formulaire";
            _LOG.info("Authentication response 404");
        }
        else if(response.statusCode == 401)
        {
            errorMsg = "Accès Refusé";
            _LOG.info("Authentication response 401");
        }
        else 
        {
            errorMsg = "Le Service est indisponible";
            _LOG.info("Internal error is produced");
        }
    }

    void onSignUp()
    {
        _router.navigate(["dashboard"]); // Navigate to Dashboard
    }
}