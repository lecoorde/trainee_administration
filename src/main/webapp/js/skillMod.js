'use strict';
angular.module('skillMod', [])
    .factory('SkillService', ['$http', '$q', function ($http, $q) {
        return {
            getSkill: function (id) {
                return $http.get('http://localhost:7070/skills/' + id).then(function (response) {
                    return response.data;
                }, function (errResponse) {
                    console.error('Error while fetching skill');
                    return $q.reject(errResponse);
                });

            },
            getSkills: function () {
                return $http.get('http://localhost:7070/skills/list').then(function (response) {
                    return response.data;
                }, function (errResponse) {
                    console.error('Error while fetching skills');
                    return $q.reject(errResponse);
                });
            }
        }
    }])

    .controller('SkillCtrl', ['$scope', 'SkillService', function ($scope, SkillService) {
        var self = this;
        self.skills = [];
        self.skill = {id: null, name: '', description: ''};
        self.fetchAllSkills = function () {
            SkillService.getSkills().then(
                function (d) {
                    self.skills = d;
                },
                function (errResponse) {
                    console.error('Error while fetching skills');
                }
            );
        };
        self.getSingleSkill = function (id) {
            SkillService.getSkill(id).then(function (d) {
                    self.skill = d;
                },
                function (errResponse) {
                    console.error('Error while fetching skill');
                }
            )
        };
        self.fetchAllSkills();
    }]);