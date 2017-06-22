import 'dart:async';
import 'dart:convert';
import 'dart:html' as html;

import 'package:angular2/angular2.dart';
import 'package:http/http.dart';
import 'package:angular2/router.dart';
import 'package:webui/src/model/auth_request.dart';
import 'package:webui/src/model/auth_response.dart';
import 'package:webui/src/service/local_storage_service.dart';


@Injectable()
class AuthenticationService {
  final Map<String,String> _headers = {"Content-Type":"application/json"};
  final String _url = "/api/auth";
  final String _urlTest = "http://localhost:9090/api/auth";
  final Client _client;
  final LocalStorageService _serviceStorage;
  final Router _router;
  
  AuthenticationService(this._client,this._serviceStorage,this._router);

  Future<bool> login(AuthenticationRequest request) async {
    String login = request.login;
    String password = request.password;
    if((login != null && login.isNotEmpty) && (password != null && password.isNotEmpty)) {
      String json = JSON.encode(request.toMap());
      return await _client.post(_urlTest,body: json,headers: _headers)
        .catchError(null)
        .then((response) => verifyResponseStatys(response));
    }
    html.window.console.info("Login or Password are null or empty");
    return false;
  }

  bool verifyResponseStatys(Response response) {
    if(response != null) {
      var statusCode = response.statusCode;
      if(statusCode == 201) {
        html.window.console.info("Status Response is 201");
        String json = response.body;
        Map<String,dynamic> jsonDecoded = JSON.decode(json);
        _saveUserInfoInLocalStorage(jsonDecoded);
        return true;
      }
      else if(statusCode == 401)
        html.window.console.info("Status Response is 401");
      else 
        html.window.console.info("Status Response is 404");
    }
    return false;
  }

  void redirectToViewDashboard() {
      html.window.console.info("Redirect To Home Component");
      _router.navigate(const ["/Dashboard"]);
  }

  void _saveUserInfoInLocalStorage(Map<String,dynamic> body) {
    AuthenticationResponse authResponse = new AuthenticationResponse.fromMap(body);
    _serviceStorage.saveElement("token", authResponse.token);
    String id = authResponse.userId.toString();
    _serviceStorage.saveElement("id",id);
    _serviceStorage.saveElement("firstName",authResponse.firstName);
    _serviceStorage.saveElement("lastName", authResponse.lastName);
    _serviceStorage.saveElement("words", authResponse.words);
    _serviceStorage.saveElement("isAuthenticated", "true");
  }
}
