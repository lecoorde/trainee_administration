'use strict';
angular.module('tutorialApp', ['ngRoute'])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {templateUrl: 'start.html'})
            .when('/about', {template: 'Über die Trainees'})
            .otherwise({redirectTo: '/'});
    })
    .factory('TraineeService', ['$http', function ($http) {
        return {
            getTrainee: function (id) {
                $http.get('http://localhost:7070/oneuser/' + id).then(function (response) {
                    return response.data;
                });

            },
            getTrainees:function(){
                $http.get('http://localhost:7070/list').then(function (response) {
                    return response.data;
                });
            }
        }
    }])
    .controller('TraineeCtrl', function ($scope, $http) {
        $http.get('http://localhost:7070/user').then(function (response) {
            $scope.trainees = response.data;
        });

    })
    .controller('OneTraineeCtrl',['$scope','TraineeService',function($scope,TraineeService){
        var self=this;
        self.trainees=[];
        self.fetchAllUsers=function(){
            TraineeService.getTrainees().then(
                function(d){
                    self.trainees=d;
                }
            );
        };
        self.fetchAllUsers();
    }]);
///**
// * Created by LECOORDE on 15.02.2016.
// */
//'use strict';
//
//angular.module('tutorialApp', ['ngAnimate', 'ngRoute','ngResource'])
//
//    .directive('price', function () {
//        return {
//            restrict: 'E',
//            scope: {
//                value: '='
//            },
//            template: '<span ng-show="value == 0">kostenlos</span>' +
//            '<span ng-show="value > 0">{{value | currency}}</span>'
//        }
//    })
//    //.factory('UserService', ['$http', '$q', function($http, $q) {
//    //
//    //    return {
//    //
//    //        fetchAllUsers: function () {
//    //            return $http.get('http://localhost:8090/user/')
//    //                .then(
//    //                    function (response) {
//    //                        return response.data;
//    //                    },
//    //                    function (errResponse) {
//    //                        console.error('Error while fetching users');
//    //                        return $q.reject(errResponse);
//    //                    }
//    //                );
//    //        }
//    //    };
//    //}])
//    .factory('Cart', function () {
//        var items = [];
//        return {
//            getItems: function () {
//                return items;
//            },
//            addArticle: function (article) {
//                items.push(article);
//            },
//            sum: function () {
//                return items.reduce(function (total, article) {
//                    return total + article.price;
//                }, 0);
//            }
//        };
//    })
//    .controller('ArticlesCtrl', function ($scope, $http, Cart) {
//        $scope.cart = Cart;
//        $http.get('http://localhost:8090/user/').then(function (articlesResponse) {
//            $scope.articles = articlesResponse.data;
//        });
//    })
//    .controller('CartCtrl', function ($scope, Cart) {
//        $scope.cart = Cart;
//    })
//    //.controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
//    //    var self = this;
//    //    //self.user = {id: null, name: '', preName: '', birthDate: '', professional:'',};
//    //    self.users = [];
//    //
//    //    self.fetchAllUsers = function () {
//    //        UserService.fetchAllUsers()
//    //            .then(
//    //                function (d) {
//    //                    self.users = d;
//    //                },
//    //                function (errResponse) {
//    //                    console.error('Error while fetching Currencies');
//    //                }
//    //            );
//    //    };
//    //}]);