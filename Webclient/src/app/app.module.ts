import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { MaterialModule } from '@angular/material';

import { AppComponent } from './app.component';
import { WebclientRoutingModule } from './app-routing.module';
import { HomeComponent } from './home/home.component';
import { PersonpanelComponent } from './personpanel/personpanel.component';
import { ItempanelComponent } from './itempanel/itempanel.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PersonpanelComponent,
    ItempanelComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    MaterialModule.forRoot(),
    WebclientRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
