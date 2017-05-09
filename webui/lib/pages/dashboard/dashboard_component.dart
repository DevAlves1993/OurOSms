import 'package:angular2/angular2.dart';
import 'package:angular2/router.dart';
import 'package:webui/service/local_storage_service.dart';

@Component(selector: "dash-component"
    ,templateUrl: "dashboard.html"
    ,styleUrls: const ["dashboard.css","../../asset/w3.css"]
    ,providers: const [LocalStorageService])
class DashboardComponent implements CanActivate
{
    final LocalStorageService _localStorage;
    Router _router;

    DashboardComponent(this._localStorage,this._router);

    @override
    Function get fn => () => _localStorage.verifyIfCurrentUserExist() ? null : _redirectionToLogin();

    _redirectionToLogin()
    {
        _router.navigateByUrl("/login");
    }
}