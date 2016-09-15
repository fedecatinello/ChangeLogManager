'use strict';

app.controller('ProjectController', function (projectService, $timeout) {

  var self = this;

  self.errors = [];
  self.projects = [];

  function notifyError(mensaje) {
    self.errors.push(mensaje);
    self.getProjects();
    $timeout(function () {
      while (self.errors.length > 0)
        self.errors.pop();
    }, 3000);
  }

  function mapToProject(jsonProject) {
    return Project.asProject(jsonProject);
  }

  // TRAER LOS PROYECTOS
  this.getProjects = function () {
    projectService.findAll(function (response) {
      self.projects = _.map(response.data, mapToProject);
    }, notifyError);
  };

  // EDITAR PROYECTO
  this.edit = function (proj) {
    //Buscar el proyecto y editarlo
    projectService.update(proj, function () {
      self.getProjects();
    }, notifyError);
  };

  // AGREGAR VERSION A UN PROYECTO
  this.addVersion = function (proj) {
    self.tareaSeleccionada = proj;
  };

  // MOSTRAR VERSIONES DE UN PROYECTO
  this.getVersions = function (proj) {
    self.tareaSeleccionada = proj;
  };

  // Se produce la asignación propiamente dicha
  this.asignar = function (asignatario) {
	self.tareaSeleccionada.asignadoA = asignatario;
    projectService.update(self.tareaSeleccionada, function () {
      }, notificarError);
  };


  self.getProjects();
});
