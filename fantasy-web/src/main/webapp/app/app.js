const app = angular.module('load-balancer-sample-app-ui', ['ngRoute', 'ui.router']);

app.config(($routeProvider) => {
  $routeProvider
    .when('/', {
      template: '<home/>',
      label: 'Home'
    });
});

app.component('home', {
  templateUrl: 'home.html',
  controller: function() {
    let that = this;
    that.message = "HELLO wORLD";
  }
});