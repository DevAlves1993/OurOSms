import 'dart:html' as html;

import 'package:angular2/angular2.dart';

@Injectable()
class LocalStorageService {

  LocalStorageService();

  void saveElement(String key,String token) {
    html.window.localStorage[key] = token;
  }

  String loadElement(String key) {
    return html.window.localStorage[key];
  }
}
