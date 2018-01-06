var app = angular.module('app', []);
app.controller('logincontroller', function($scope, $http, $location, $window) {
    	$scope.submitForm = function(){
            $scope.loginResultMessage = "";
            var username = $scope.username;
            var password = $scope.password;
            var isValid = $scope.validateInput(username, password);

            if (isValid) {
                $scope.login(username, password);
            }
	}

    $scope.validateInput = function(username, password){
        var isValid = true;
        if (username == null || username.length == 0) {
            $scope.usernameError = "Please enter username";
            isValid = false;
        } else {
            $scope.usernameError = "";
        }
        if (password == null || password.length == 0) {
            $scope.passwordError = "Please enter password";
            isValid = false;
        } else {
            $scope.passwordError = "";
        }

        return isValid;
    }

    $scope.login = function(username, password){
        var url = $location.absUrl();
        var authorization = "Basic " + btoa(username + ":" + password);
        var config = {
            headers : {
                'Accept': 'application/json',
                'authorization' : authorization
            }
        }
        $http.defaults.headers.common.Authorization = authorization;
        $http.post(url, config).then(function (response) {
            $scope.handleLoginSuccess(authorization);
        }, function error(response) {
            $scope.loginResultMessage = response.data.message;
        });
    }

    $scope.handleLoginSuccess = function(authorization){
        $window.sessionStorage.setItem("authorization",authorization);
        $window.sessionStorage.setItem("authenticated",true);
        $scope.goToCalculatorPage();
    }

    $scope.goToCalculatorPage = function(){
        var url = $location.protocol() + "://" + location.host;
        $window.location.href = url;
    }

    $scope.loginResultMessage = "";

    $scope.signup = function(){
        var url = $location.protocol() + "://" + location.host + "/signup";
	    $window.location.href = url;
    }
});
