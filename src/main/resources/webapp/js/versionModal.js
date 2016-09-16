'use strict';

app.directive('versionModal', function () {
  return {
    restrict: 'E',
    templateUrl: 'templates/version_details.html',
    scope: {
      project: "="
    },
    controllerAs: 'versionCtrl',
    bindToController: true,
    controller: function () {

      var self = this;

      self.errors = [];

      //if (self.project.versions.length == 0)
      // self.errors.push("No se encontraron versiones para este proyecto");

    }
  };
});
