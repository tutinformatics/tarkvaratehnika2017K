export class App {
configureRouter(config, router) {
    this.router = router;
    config.title = 'My Aurelia äpp';

    config.map([
      { route: ['', 'home'],       name: 'home',       moduleId: 'home/index', title: "Esileht", nav: true },
      { route: 'people', name: 'people',  moduleId: 'people/people', title: "Inimesed",  nav: true }
    ]);
  }
}
