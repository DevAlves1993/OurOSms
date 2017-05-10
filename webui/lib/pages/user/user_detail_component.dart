import 'package:angular2/angular2.dart';
import 'package:angular2/router.dart';
import 'package:webui/model/user.dart';

@Component(selector: "user-details-component"
    ,templateUrl: "user_detail.html"
    ,styleUrls: const ["user_detail.css","../../asset/w3.css"])
class UserDetais implements OnInit{

    User user;
    final RouteParams _routerParams;
    // final UserService service; 

    UserDetais(this._routerParams);
    
    @override
    ngOnInit() {
    // TODO: implement ngOnInit
        int id = _getId();
        if(id != -1)
        {
            // user = service.find(id);
        }
    }

    int _getId()
    {
        String id = _routerParams.get("id");
        if(id != null)
            return int.parse(id,onError: (s) => -1);
        return -1;
    }
}