var app = angular.module('app', []);
app.controller('calculatorcontroller', function($scope, $http, $location, $window) {
    var authenticated = $scope.name = $window.sessionStorage.getItem("authenticated");
    if (authenticated == null || authenticated == 'false' ){
        $window.location.href = 'login';
    }

    var authorization = $window.sessionStorage.getItem("authorization");
    var data = "";
    var calculationRequest = "";

	$scope.submitForm = function(){
        var operation= $scope.operation;
        var firstNumber = $scope.firstNumber;
        var secondNumber = $scope.secondNumber;

        var isValid = $scope.validateInput(firstNumber, secondNumber, operation);

        if (isValid) {
            $scope.calculate();
        }
	}

    $scope.validateInput = function(firstNumber, secondNumber, operation){
        var isValid = true;
        var needCheckSecondNumber = true;

        if (firstNumber == null || firstNumber.length == 0 || isNaN(firstNumber)) {
            $scope.firstNumberError = "Please enter number";
            isValid = false;
        } else {
            $scope.firstNumberError = "";
        }

        if (operation == null || operation.length == 0) {
            $scope.operationError = "Please select operation";
            isValid = false;
        } else {
            $scope.operationError = "";
            if (operation == 'square'){
                calculationRequest = "/calculateOneParam";
                needCheckSecondNumber = false;
                data = {
                    type: operation,
                    param: firstNumber
                };
            } else {
                calculationRequest = "/calculateTwoParam";
                data = {
                    type: operation,
                    firstParam: firstNumber,
                    secondParam: secondNumber
                };
            }
        }
        if (needCheckSecondNumber){
            if (secondNumber == null || secondNumber.length == 0 || isNaN(secondNumber)) {
                $scope.secondNumberError = "Please enter number";
                isValid = false;
            } else {
                $scope.secondNumberError = "";
            }
        }

        return isValid;
    }

    $scope.calculate = function(){
        var url = $location.absUrl() + calculationRequest;
        var config = {
            headers : {
                'Accept': 'application/json',
                'authorization' : authorization
            }
        }
        $http.defaults.headers.common.Authorization = authorization;
        $http.post(url, data, config).then(function (response) {
            $scope.calculateResultMessage = response.data;
        }, function error(response) {
            $scope.calculateResultMessage = response.data.message;
        });
    }

    $scope.viewHistory = function(){
        $window.location.href = '/history';
    }

    $scope.logout = function(){
        $window.sessionStorage.removeItem("authorization")
        $window.sessionStorage.setItem("authenticated",false);
        $window.location.href = '/login';
    }
});
