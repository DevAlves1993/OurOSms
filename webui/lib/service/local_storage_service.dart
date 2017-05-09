import 'dart:convert';
import 'dart:html';

import 'package:angular2/angular2.dart';
import 'package:webui/model/user.dart';

@Injectable()
class LocalStorageService {
    Storage _storage;
    final String _CURRENT_USER_KEY = "currentUser";

    LocalStorageService(this._storage);

    void saveUserInfo(UserInfo userInfo)
    {
        String value = JSON.encode(userInfo);
        _storage.addAll({_CURRENT_USER_KEY: value});
    }

    bool verifyIfCurrentUserExist()
    {
        return _storage.containsKey(_CURRENT_USER_KEY);
    }
}