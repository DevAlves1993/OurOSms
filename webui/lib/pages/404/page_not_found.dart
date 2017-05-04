import 'package:angular2/angular2.dart';
import 'package:angular2/platform/common.dart';
import 'package:angular2/router.dart';
import 'package:logging/logging.dart';

@Component(selector: "page-not-found",
    templateUrl: "page_not_found.html",
    styleUrls: const ["page_not_found.css"])
class PageNotFound {
    Router _router;
    Location _location;
    final Logger _LOG = new Logger("PageNotFound");

    PageNotFound(this._router){
        _LOG.info("The Internal Error Page is shown");
    }

    void back(){
        _location.back();
    }
}