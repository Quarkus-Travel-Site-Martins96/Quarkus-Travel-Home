import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { ShowcaseComponent } from "./showcase/showcase.component";
import { OffersComponent } from './offers/offers.component';

@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    ShowcaseComponent,
    OffersComponent
  ],
  imports: [
    BrowserModule,
	HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
