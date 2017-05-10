import 'dart:async';
import 'dart:convert';

import 'package:angular2/core.dart';
import 'package:http/http.dart';
import 'package:webui/model/user.dart';
import 'package:webui/service/local_storage_service.dart';

@Injectable()
class LoginService
{
    Client _client;
    LocalStorageService _storageService;
    LoginService(this._storageService,this._client);
    
    Future submitAuthentication(AuthenticationBody request) async
    {
       return await _client.post("/api/auth",body: JSON.encode(request));
    }

    void saveUserInfo(UserInfo user) 
    {
        _storageService.saveUserInfo(user);
    }

    UserInfo obtainCurrentUser()
    {
        return _storageService.currentUserInfo();
    }
}

class AuthenticationBody {
    String login;
    String password;

    AuthenticationBody(this.login,this.password);
}

