function Project() {

  this.id = null;
  this.name = null;
  this.versions = [];

}

Project.asProject = function (jsonProject) {
  return angular.extend(new Project(), jsonProject);
};

