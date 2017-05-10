import 'package:angular2/angular2.dart';
import 'package:webui/model/user.dart';
import 'package:webui/service/authentication/login_service.dart';

@Injectable()
class ApiService {

    LoginService _service;

    ApiService(this._service);

    Map<String,String> createHeaderAuthorisation() {
        String authorisation = _getAuthorisation(_service.obtainCurrentUser());
        if(authorisation != null && authorisation.isNotEmpty)
        {
            Map header = {"accept": "application/json","Authorization": "Bearer "+authorisation};
            return header;
        }
        return null;   
    }

    Map<String,String> createAuthorisationForPostOrPut() {
        String authorisation = _getAuthorisation(_service.obtainCurrentUser());
        if(authorisation != null && authorisation.isNotEmpty)
        {
            Map header = {
                        "Authorisation":"Bearer "+authorisation,
                        "content-type":"application/json",
                        "accept":"application/json"
                    };
            return header;
        }
        return null;
    }
    String _getAuthorisation(UserInfo userInfo)
    {
        return userInfo.token;
    }
}