// Copyright (c) 2017, Christian Amani. All rights reserved. Use of this source code
// is governed by a BSD-style license that can be found in the LICENSE file.

import 'package:angular2/angular2.dart';
import 'package:angular2/platform/browser.dart';

import 'package:angular2/router.dart';
import 'package:http/http.dart';
import 'package:webui/app_component.dart';
import 'package:http/browser_client.dart';
import 'package:webui/src/dashboard/dashboard_component.dart';



void inject(ComponentRef cmp) {
  myInjector = cmp.injector;
}

void main() {
  bootstrap(AppComponent, [
    provide(Client, useFactory: () => new BrowserClient(), deps: []),
    ROUTER_PROVIDERS_COMMON
  ]).then((onValue) => inject(onValue));
}

