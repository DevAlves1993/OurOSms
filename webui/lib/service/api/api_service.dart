import 'package:webui/service/authentication/login_service.dart';

class ApiService {

    Map<String,String> createHeaderAuthorisationForGet() {
        String authorisation = LoginService.token;
        if(authorisation != null && authorisation.isNotEmpty)
        {
            Map header = {"accept": "application/json","Authorization": "Bearer "+authorisation};
            return header;
        }
        return null;   
    }

    Map<String,String> createAuthorisationForPostOrPut() {
        String authorisation = LoginService.token;
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
}