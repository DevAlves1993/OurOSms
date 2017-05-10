import 'dart:convert';
import 'dart:html';

import 'package:angular2/angular2.dart';
import 'package:webui/model/user.dart';

@Injectable()
class LocalStorageService {
    Storage _storage = window.localStorage;
    final String _CURRENT_USER_KEY = "currentUser";

    void saveUserInfo(UserInfo userInfo)
    {
        String value = JSON.encode(userInfo);
        _storage.addAll({_CURRENT_USER_KEY: value});
    }

    UserInfo currentUserInfo()
    {
        String json = window.sessionStorage[_CURRENT_USER_KEY];
        Map<String,dynamic> value= JSON.decode(json)["data"];
        return new UserInfo.fromJson(value);
    }

    bool verifyIfCurrentUserExist()
    {
        return _storage.containsKey(_CURRENT_USER_KEY);
    }
}