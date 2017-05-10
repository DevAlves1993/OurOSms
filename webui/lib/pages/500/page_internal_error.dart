import 'package:angular2/angular2.dart';
import 'package:angular2/router.dart';
import 'package:logging/logging.dart';

@Component(selector: "page-internla-error",
    templateUrl: "page_internal_error.html",
    styleUrls: const ["page_internal_error.css","../../asset/w3.css"])
class PageInternalError {
    Router _router;
    final Logger _LOG = new Logger("PageInternalError");

    PageInternalError(this._router)
    {
        _LOG.info("The Internal Error Page is shown");
    } 
}