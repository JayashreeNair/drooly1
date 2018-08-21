import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PageComponent} from './page/page.component';
import {BodyComponent} from './body/body.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [PageComponent, BodyComponent],
  exports: [PageComponent, BodyComponent]
})
export class SharedModule {
}
