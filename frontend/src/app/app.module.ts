//Modules
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {InfiniteScrollModule} from 'ngx-infinite-scroll'

//Components
import { AppComponent } from './app.component';
import { HeaderComponent } from 'components/header/header.component';
import { FooterComponent } from 'components/footer/footer.component';
import { BarComponent } from 'components/bar/bar.component';
import { AboutComponent } from 'components/about/about.component';
import { HomeComponent } from 'components/home/home.component';
import { BarCardComponent } from 'components/bar-card/bar-card.component';

@NgModule({
  declarations: [
    AppComponent,
    BarComponent,
    AboutComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    BarCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    InfiniteScrollModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
