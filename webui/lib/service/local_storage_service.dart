import 'dart:html';

import 'package:angular2/angular2.dart';

@Injectable()
class LocalStorageService {
    Storage _storage;

    LocalStorageService(this._storage);

    String obtainToken(String userId){
        if(_storage.containsKey(userId))
            return window.localStorage[userId];
        return null;
    }

    void removeToken(String userId){
        if(_storage.containsKey(userId))
            _storage.remove(userId);
    }
}