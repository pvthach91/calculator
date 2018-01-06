var app = angular.module('app', []);
app.controller('signupcontroller', function($scope, $http, $location, $window) {
	$scope.submitForm = function(){
        $scope.signupResultMessage = "";
        var username = $scope.username;
        var password = $scope.password;
        var repeatPassword = $scope.repeatPassword;

        var isValid = $scope.validateInput(username, password, repeatPassword);

        if (isValid) {
            $scope.signup(username, password);
        }
	}

    $scope.validateInput = function(username, password, repeatPassword){
        var isValid = true;
        if (username == null || username.length < 3) {
            $scope.usernameError = "Please enter username at least 3 characters";
            isValid = false;
        } else {
            $scope.usernameError = "";
        }
        if (password == null || password.length < 4) {
            $scope.passwordError = "Please enter password at least 4 characters";
            isValid = false;
        } else {
            $scope.passwordError = "";
        }
        if (repeatPassword == null ||repeatPassword.length < 4) {
            $scope.repeatPasswordError = "Please enter password at least 4 characters";
            isValid = false;
        } else {
            $scope.repeatPasswordError = "";
        }
        if (isValid) {
            if (password!= repeatPassword) {
                $scope.repeatPasswordError = "Password doesn't mach";
                isValid = false;
            } else {
                $scope.repeatPasswordError = "";
            }
        }

        return isValid;
    }

    $scope.signup = function(username, password){
        var url = $location.absUrl();
        var authorization = "Basic " + btoa(username + ":" + password);
        var config = {
            headers : {
                'Accept': 'application/json',
            }
        }

        var data = {
            username: username,
            password: password
        };

        $http.post(url, data, config).then(function (response) {
            $scope.handleSignupSuccess(authorization);
        }, function error(response) {
            $scope.signupResultMessage = response.data.message;
        });
    }

    $scope.handleSignupSuccess = function(authorization){
        $window.sessionStorage.setItem("authorization",authorization);
        $window.sessionStorage.setItem("authenticated",true);
        $scope.goToCalculatorPage(authorization);
    }

    $scope.goToCalculatorPage = function(authorization){
        var url = $location.protocol() + "://" + location.host;
        var config = {
            headers : {
                'Accept': 'application/json',
                'authorization' : authorization
            }
        }
        $http.defaults.headers.common.Authorization = authorization;
        $http.get(url,  config).then(function (response) {
            $window.location.href = url;
        }, function error(response) {
        });
    }

    $scope.login = function(){
        var url = $location.protocol() + "://" + location.host + "/login";
	    $window.location.href = url;
    }
});

