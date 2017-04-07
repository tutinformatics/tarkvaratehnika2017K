import {inject} from 'aurelia-framework';
import {FetchConfig} from 'aurelia-auth';

@inject(FetchConfig)
export class App {

constructor(fetchConfig) {
	this.fetchConfig = fetchConfig;
}

configureRouter(config, router) {
    this.router = router;
    this.fetchConfig.configure();
    config.title = 'My Aurelia äpp';

    config.map([
      { route: ['', 'home'],       name: 'home',       moduleId: 'home/index', title: "Esileht", nav: true },
      { route: 'people', name: 'people',  moduleId: 'people/people', title: "Inimesed",  nav: true }
    ]);
  }
}
