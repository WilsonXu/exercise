angular.module("sportsStore")
    .constant("dataUrl", "http://10.32.154.226:2403/products")
    .controller("sportsStoreCtrl", function($scope, $http, dataUrl) {
        $scope.data = {};
        $http.get(dataUrl)
            .success(function(data){
                $scope.data.products = data;
            })
            .error(function(error){
                $scope.data.error = error;
            });
});
