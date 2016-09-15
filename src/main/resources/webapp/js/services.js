app.service('projectService', function ($http) {

  this.findAll = function (callback, errorHandler) {
    $http.get('/projects').then(callback, errorHandler);
  };

  this.update = function (project, callback, errorHandler) {
    $http.put('/projects/' + project.id, project).then(callback, errorHandler);
  };

});


app.service('usuariosService', function ($http) {

  this.findAll = function (callback, errorHandler) {
    $http.get('/usuarios').then(callback, errorHandler);
  }

});
