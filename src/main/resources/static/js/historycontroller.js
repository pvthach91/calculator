var app = angular.module('app', []);
app.controller('historycontroller', function($scope, $http, $location, $window) {
    var authenticated = $window.sessionStorage.getItem("authenticated");
    if (authenticated == null || authenticated == 'false' ){
        $window.location.href = 'login';
    }

    var authorization = $scope.name = $window.sessionStorage.getItem("authorization");

	$scope.refresh = function(){
        var url = $location.protocol() + "://" + location.host + "/histories";
        var config = {
            headers : {
                'Accept': 'application/json',
                'authorization' : authorization
            }
        }
        $http.defaults.headers.common.Authorization = authorization;
        $http.get(url, config).then(function (response) {
            $scope.histories = response.data;
        }, function error(response) {
            $scope.historyResultMessage = response.data.message;
        });
	}

    $scope.goToCalculatorPage = function(){
        var url = $location.protocol() + "://" + location.host;
        $window.location.href = url;
    }

    $scope.logout = function(){
        $window.sessionStorage.removeItem("authorization")
        $window.sessionStorage.setItem("authenticated",false);
        var url = $location.protocol() + "://" + location.host + "/login";
        $window.location.href = url;
    }

    $scope.refresh();
});
