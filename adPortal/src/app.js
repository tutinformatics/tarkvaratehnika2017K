import {inject} from 'aurelia-framework';
import {Router} from 'aurelia-router';
import {FetchConfig} from 'aurelia-auth';

@inject(Router,FetchConfig, AppRouterConfig)
export class App {

  constructor(router, fetchConfig, appRouterConfig){
    this.router = router;
    this.fetchConfig = fetchConfig;
  }

  activate(){
    this.configureRouter(this.appRouterConfig, this.router)
    this.fetchConfig.configure();
  }

configureRouter(config, router) {
    config.title = 'My Aurelia äpp';

    config.map([
      { route: ['', 'home'],       name: 'home',       moduleId: 'home/index', title: "Esileht", nav: true },
      { route: 'people', name: 'people',  moduleId: 'people/people', title: "Inimesed",  nav: true }
    ]);
  }
}
