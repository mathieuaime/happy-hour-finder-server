import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BarComponent } from 'components/bar/bar.component';
import { AboutComponent } from 'components/about/about.component';
import { HomeComponent } from 'components/home/home.component';

const routes: Routes = [
    {
        path: 'home',
        component: HomeComponent
    },
    {
        path: 'about',
        component: AboutComponent
    },
    {
        path: 'bars',
        component: BarComponent
    },
    {
        path: '',
        redirectTo: '/home',
        pathMatch: 'full'
    },
    {
        path: '**',
        redirectTo: '/home',
        pathMatch: 'full'
    }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
