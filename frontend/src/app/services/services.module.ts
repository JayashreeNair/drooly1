import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RuleServiceService} from './rule-service.service';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [],
  providers:[RuleServiceService]
})
export class ServicesModule { }
