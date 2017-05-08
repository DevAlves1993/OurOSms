import 'dart:async';
import 'dart:convert';
import 'package:angular2/angular2.dart';
import 'package:http/http.dart';
import 'package:logging/logging.dart';
import 'package:webui/model/user.dart';
import 'package:webui/service/api/api_service.dart';

@Injectable()
class UserService extends ApiService {
    final Client _client;
    final Logger _LOG = new Logger("UserService");

    UserService(this._client);

    Future<List<User>> findAll() async
    {
        Map<String,String> head = createHeaderAuthorisationForGet();
        if(head != null)
        {
            Response response = await _client.get("/api/users",headers: head);
            if(response.statusCode == 200)
            {
                String body = response.body;
                List<User> list = JSON.decode(body);
                _LOG.info("Request for get all User was perform with success. \nStatus code is 200");
                return list;
            }
            else if(response.statusCode == 404)
            {
                _LOG.info("Request get all user have fail.\nStatus code is 404");
            }
        }
        return null;
    }

    Future<User> find(String id) async 
    {
        Map<String,String> head = createHeaderAuthorisationForGet();
        if(head != null)
        {
            Response response = await _client.get("/api/users/id",headers: head);
            if(response.statusCode == 200)
            {
                String body = response.body;
                User user = JSON.decode(body);
                _LOG.info("The request to success. \nStatus code is 200");
                return user;
            }
            else if(response.statusCode == 404)
            {
                _LOG.info("The request to fail. \Status code is 404");
            }
        }
        return null;
    }

    Future<User> save(User user,bool update) async 
    {
        Map<String,String> head = createAuthorisationForPostOrPut();
        if(head != null)
        {
            Response response;
            if(update)
                response = await _client.put("/api/auth/users",headers: head,body: JSON.encode(user));
            else
                response = await _client.post("/api/auth/users",headers: head,body: JSON.encode(user));
            if(response.statusCode == 201 || response.statusCode == 200)
            {
                String body = response.body;
                User user = JSON.decode(body);
                _LOG.info("The request to success. \Status code is "+response.statusCode.toString());
                return user;
            }
            else if(response.statusCode == 404)
            {
                _LOG.info("The request to fail. \Status code is 404");
            }
            else if(response.statusCode >= 500)
            {
                _LOG.info("The request to fail. \Status code is 500");
            }
        }
        return null;
    }
}