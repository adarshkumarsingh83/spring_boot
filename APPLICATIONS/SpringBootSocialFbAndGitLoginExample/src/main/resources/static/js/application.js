/*

* @author Adarsh Kumar
* @author $LastChangedBy: Adarsh Kumar$
* @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
* @Espark @copyright all right reserve
*/
angular
    .module("app", [])
    .config(
    function($httpProvider) {
        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    }).controller("home", function($http, $location) {
        var indexPage = this;
        $http.get("/user").success(function(data) {
            if (data.name) {
                indexPage.user = data.name;
                indexPage.message = data.message;
                indexPage.authenticated = true;
            } else {
                indexPage.user = "N/A";
                indexPage.authenticated = false;
            }
        }).error(function() {
                indexPage.user = "N/A";
                indexPage.authenticated = false;
            });
        indexPage.logout = function() {
            $http.post('logout', {}).success(function() {
                indexPage.authenticated = false;
                $location.path("/");
            }).error(function(data) {
                    console.log("Logout failed")
                    indexPage.authenticated = false;
                });
        };
    });