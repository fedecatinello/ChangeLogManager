'use strict';

app.config(function ($stateProvider, $urlRouterProvider) {

  $urlRouterProvider.otherwise("/projects");

  $stateProvider
    .state('projects', {
      url: '/projects',
      templateUrl: 'templates/projects.html',
      controller: 'ProjectController as projCtrl',
      data: {},
      resolve: {}
    })
});
