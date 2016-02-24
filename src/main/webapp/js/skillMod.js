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
            },
            getTraineesBySkillId: function (id) {
                return $http.get('http://localhost:7070/skills/trainee_list/' + id).then(function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while fetching trainees');
                        return $q.reject(errResponse)
                    });
            },
            createSkill: function (skill) {
                return $http.post('http://localhost:7070/skills/createSkill/', skill)
                    .then(
                        function (response) {
                            return response.data;
                        },
                        function (errResponse) {
                            console.error('Error while creating skill');
                            return $q.reject(errResponse);
                        }
                    );
            },
            deleteSkill: function (id) {
                return $http.post('http://localhost:7070/skills/delete/' + id)
                    .then(function (response) {
                            return response.data
                        }, function (errResponse) {
                            console.error('Error while deleting skill');
                            return $q.reject(errResponse);
                        }
                    );
            }
        }
    }])

    .controller('SkillCtrl', ['$scope', 'SkillService', function ($scope, SkillService) {
        var self = this;
        self.skills = [];
        self.trainees = [];
        self.skill = {id: null, name: '', description: ''};
        self.createableSkill = {
            id: null
    }

        $scope.filter_skill_id = '';
        $scope.filter_skill_name = '';
        $scope.filter_skill_description = '';

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
        self.getTraineesBySkillId = function (id) {
            SkillService.getTraineesBySkillId(id).then(function (d) {
                    self.trainees = d;
                },
                function (errResponse) {
                    console.error('Error while fetching trainees')
                }
            )
        };
        self.confirmDelete = function confirmDelete(id) {

            if (confirm("Möchten Sie diesen Skill wirklich löschen?") == true) {
                SkillService.deleteSkill(id);
            }
        };
        self.submitSkill=function(){
            SkillService.createSkill(self.createableSkill);
        };
        self.fetchAllSkills();
    }]);