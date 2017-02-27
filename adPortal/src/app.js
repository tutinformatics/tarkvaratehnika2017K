export class App {
configureRouter(config, router) {
    this.router = router;
    config.title = 'My Aurelia äpp';

    config.map([
      { route: ['', 'home'],       name: 'home',       moduleId: 'home/index' },
      { route: 'people', name: 'people',  moduleId: 'people',   nav: true }
    ]);
  }

}
