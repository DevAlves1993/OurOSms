import 'dart:async';
import 'dart:convert';
import 'dart:html' as html;

import 'package:angular2/core.dart';
import 'package:http/http.dart';
import 'package:webui/model/user.dart';

@Injectable()
class LoginService
{
    UserInfo _userInfo;
    Client _client;
    html.Storage _storage;
    LoginService(this._storage,this._client);

    UserInfo get token => _userInfo;

    Future submitAuthentication(AuthenticationBody request) async
    {
       return await _client.post("/api/auth",body: JSON.encode(request));
    }


    void saveUserInfo(UserInfo user) 
    {
        _storage.addAll({user.userId: user.token});
    }
}

class AuthenticationBody {
    String login;
    String password;

    AuthenticationBody(this.login,this.password);
}
