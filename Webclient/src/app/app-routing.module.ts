import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home';
import { ItempanelComponent } from './itempanel';
import { PersonpanelComponent } from './personpanel';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'persons', component: PersonpanelComponent },
  { path: 'items', component: ItempanelComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: []
})
export class WebclientRoutingModule { }
