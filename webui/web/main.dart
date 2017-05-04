// Copyright (c) 2017, Christian Amani. All rights reserved. Use of this source code
// is governed by a BSD-style license that can be found in the LICENSE file.

import 'package:angular2/core.dart';
import 'package:angular2/platform/browser.dart';

import 'package:webui/app_component.dart';

void main() {
  bootstrap(AppComponent,
      [provide(BrowserClient, useFactory: () => new BrowserClient(),deps:[]
  )]);
}
