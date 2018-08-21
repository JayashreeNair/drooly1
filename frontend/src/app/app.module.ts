import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {SharedModule} from './shared/shared.module';
import { ReportComponent } from './report/report.component';
import {ServicesModule} from './services/services.module';

@NgModule({
  declarations: [
    AppComponent,
    ReportComponent
  ],
  imports: [
    BrowserModule, SharedModule, ServicesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
