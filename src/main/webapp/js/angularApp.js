var app = angular.module('app', []);//20/10/2018

app.controller('postOrganizationsControllerUpdate', function ($scope, $http, $location, FactoryOrgId) {
    $scope.model = FactoryOrgId.organization;
    $scope.showOrganization = false;

    $scope.getByIdOrganization = function () {
        var url = $location.absUrl() + "/api/organization/" + FactoryOrgId.organization.id; //$scope.organizationId;

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        var data = {
            name: $scope.name,
            fullName: $scope.fullName,
            inn: $scope.inn,
            kpp: $scope.kpp,
            address: $scope.address,
            phone: $scope.phone,
            isActive: $scope.isActive
        };

        $http.get(url, config).then(function (response) {

            if (response.data.result == "success") {
                //$scope.organization = response.data;
                var list = response.data;
                $scope.showOrganization = true;
                //$scope.name = $scope.organization.data.name;
                $scope.name = list.data.name;
                $scope.fullName = list.data.fullName;
                $scope.inn = list.data.inn;
                $scope.kpp = list.data.kpp;
                $scope.address = list.data.address;
                $scope.phone = list.data.phone;
                $scope.isActive = list.data.active;
                //$scope.isActive = $scope.organization.data.isActive;

            } else {
                $scope.getResultMessage = "Organization Data Error!";
            }

        }, function (response) {
            $scope.getResultMessage = "Fail!";
        });

    }
    $scope.postUpdateOrganization = function () {
        var url = $location.absUrl() + "/api/organization/update";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }

        var data = {
            id: FactoryOrgId.organization.id, //$scope.organizationId,
            name: $scope.name,
            fullName: $scope.fullName,
            inn: $scope.inn,
            kpp: $scope.kpp,
            address: $scope.address,
            phone: $scope.phone,
            isActive: $scope.isActive
        };

        $http.post(url, data, config).then(function (response) {
            $scope.postResultMessage = "Sucessful!";
            $scope.list = response.data;

        }, function (response) {
            $scope.postResultMessage = "Fail!";
        });

        $scope.name = "";
        $scope.fullName = "";
        $scope.inn = "";
        $scope.kpp = "";
        $scope.address = "";
        $scope.phone = "";
        $scope.isActive = "";
    }

});

app.controller('postOrganizationsControllerSave', function ($scope, $http, $location) {
    $scope.submitForm = function () {
        var url = $location.absUrl() + "/api/organiazation/save";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        var data = {
            name: $scope.name,
            fullName: $scope.fullName,
            inn: $scope.inn,
            kpp: $scope.kpp,
            address: $scope.address,
            phone: $scope.phone,
            isActive: $scope.isActive
        };
        $http.post(url, data, config).then(function (response) {
            $scope.postResultMessage = "Sucessful!";
        }, function (response) {
            $scope.postResultMessage = "Fail!";
        });

        $scope.name = "";
        $scope.fullName = "";
        $scope.inn = "";
        $scope.kpp = "";
        $scope.address = "";
        $scope.phone = "";
        $scope.isActive = "";
    }
});

app.controller('postOrganizationsControllerDelete', function ($scope, $http, $location) {

    $scope.showOrganization = false;

    $scope.deleteOrganizationById = function () {
        var url = $location.absUrl() + "/api/organization/delete";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }

        var data = {
            id: $scope.organizationId
        };

        $http.post(url, data, config).then(function (response) {
            $scope.message = response.data.result;
            if (response.data.result == "success") {
                // $scope.postResultMessage = "Sucessful!!!";
                $scope.showOrganization = true;
                $scope.allorganizations = response.data;
            }
        }, function (response) {
            $scope.postResultMessage = "Fail!";

        });

    }

});

app.controller('postOrganizationsControllerList', function ($scope, $http, $location) {

    $scope.showAllOrganizations = false;

    $scope.submitForm = function () {
        var url = $location.absUrl() + "/api/organization/list";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        var data = {
            name: $scope.name,
            inn: $scope.inn,
            isActive: $scope.isActive
        };
        $http.post(url, data, config).then(function (response) {
            $scope.postResultMessage = response.data.result;
            $scope.showAllOrganizations = false;
            if (response.data.result == "success") {
                $scope.allorganizations = response.data;
                $scope.showAllOrganizations = true;
            } else {
                // $scope.postResultMessage = "get All Organizations Data Error!";
                $scope.postResultMessage = response.data.error;
            }
        }, function (response) {
            $scope.showAllOrganizations = false;
            //$scope.postResultMessage = "Fail!";
            $scope.postResultMessage = response.data.error;
        });

    }
});

app.controller('getAllOrganizationsControllerBrief', function ($scope, $http, $location) {

    $scope.showAllOrganizations = false;

    $scope.getAllOrganizations = function () {
        var url = $location.absUrl() + "/api/organizations"; // "findall";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        $http.get(url, config).then(function (response) {

            if (response.data.result == "success") {
                $scope.allOrganizations = response.data;
                $scope.showAllOrganizations = true;
            } else {
                $scope.getResultMessage = "get All Organizations Data Error! "
            }
        }, function (response) {
            $scope.getResultMessage = "Fail!";

        });
    }


});

app.controller('getOrganizationControllerById', function ($scope, $http, $location) {

    $scope.showOrganization = false;

    $scope.getOrganization = function () {
        var url = $location.absUrl() + "/api/organization/" + $scope.organizationId;

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }

        $http.get(url, config).then(function (response) {

            if (response.data.result == "success") {
                $scope.organization = response.data;
                $scope.showOrganization = true;

            } else {
                $scope.getResultMessage = "Organization Data Error!";
            }

        }, function (response) {
            $scope.getResultMessage = "Fail!";
        });

    }
});

app.controller('getAllOrganizationsController', function ($scope, $http, $location) {

    $scope.showAll = false;
    $scope.showAllOrganizationsBrief = false;
    $scope.buttonSize = "Full";
    $scope.buttonView = "Show";

    $scope.getAllOrganizationsFull = function () {
        var url = $location.absUrl() + "/api/organizations"; // "findall";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        if ($scope.showAll == false) {
            $http.get(url, config).then(function (response) {
                if (response.data.result == "success") {
                    $scope.allOrganizations = response.data;
                    $scope.showAll = true;
                    $scope.buttonView = "Hide";
                } else {
                    $scope.getResultMessage = "get All Organizations Data Error!";
                }
            }, function (response) {
                $scope.getResultMessage = "Fail!";
            });
        } else {
            $scope.showAll = false;
            $scope.buttonView = "Show";
        }
    }
    $scope.getAllOrganizationsBrief = function () {
        if ($scope.showAll == true) {
            if ($scope.showAllOrganizationsBrief == true) {
                $scope.showAllOrganizationsBrief = false;
                $scope.buttonSize = "Full";
            } else {
                $scope.showAllOrganizationsBrief = true;
                $scope.buttonSize = "Brief";
            }
        }
    }

});

app.controller('getAllOfficesController', function ($scope, $http, $location) {

    $scope.showAll = false;
    $scope.showAllOfficesBrief = false;
    $scope.buttonSize = "Full";
    $scope.buttonView = "Show";

    $scope.getAllOfficesFull = function () {
        var url = $location.absUrl() + "/api/offices"; // "findall";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        if ($scope.showAll == false) {
            $http.get(url, config).then(function (response) {
                if (response.data.result == "success") {
                    $scope.allOffices = response.data;
                    $scope.showAll = true;
                    $scope.buttonView = "Hide";

                } else {
                    $scope.getResultMessage = "get All Offices Data Error!";
                }
            }, function (response) {
                $scope.getResultMessage = "Fail!";
            });
        } else {
            $scope.showAll = false;
            $scope.buttonView = "Show";
        }
    }
    $scope.getAllOfficesBrief = function () {
        if ($scope.showAll == true) {
            if ($scope.showAllOfficesBrief == true) {
                $scope.showAllOfficesBrief = false;
                $scope.buttonSize = "Full";
            }
            else {
                $scope.showAllOfficesBrief = true;
                $scope.buttonSize = "Brief";
            }
        }
    }

});

app.controller('getOfficeControllerGetById', function ($scope, $http, $location, FactoryOrgId, FactoryOffice) {
    $scope.model = FactoryOrgId.organization;
    $scope.office = FactoryOffice.office;
    $scope.modelOffice = FactoryOffice.modelOffice;

    $scope.getOfficeById = function () {
        var url = $location.absUrl() + "/api/office/" + $scope.office.id;

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }

        $http.get(url, config).then(function (response) {

            if (response.data.result == "success") {

                //$scope.show = true;
                var list = response.data.data;

                $scope.setView($scope.office.id, list.name, list.address, list.phone, list.active);

                FactoryOffice.updateOfficeData($scope.office.id, list.name, list.address, list.phone, list.active);
                FactoryOffice.modelOffice.resultMessage = response.data.result;
                //FactoryOrgId.setOrgId($scope.modelOffice.orgId);
                FactoryOrgId.setOrgId(response.data.data.organization.id);
            } else {
                //$scope.getResultMessage = "Offices Data Error!";
                FactoryOffice.modelOffice.resultMessage = response.data.error;
                $scope.setView('', '', '', '', '');
                FactoryOrgId.setOrgId('');
            }

        }, function (response) {
            //$scope.getResultMessage = "Fail!";
            FactoryOffice.modelOffice.resultMessage = response.data.error;
            $scope.setView('', '', '', '', '');
            FactoryOrgId.setOrgId('');
        });
    }
});

app.controller('postOfficeControllerListbyOrgId', function ($scope, $http, $location, FactoryOrgId, FactoryOffice) {
    $scope.model = FactoryOrgId.organization;
    $scope.office = FactoryOffice.office;
    $scope.modelOffice = FactoryOffice.modelOffice;
    $scope.modelOffice.showAll = false;
    //$scope.showAllOrgId = false;

    $scope.hideListByOrgId = function () {
        $scope.modelOffice.showAll = false;
    }

    $scope.postOfficeListByOrgId = function () {
        var url = $location.absUrl() + "/api/office/list";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        var data = {
            orgId: FactoryOrgId.organization.id,
            name: $scope.name,
            phone: $scope.phone,
            isActive: $scope.isActive
        };
        $http.post(url, data, config).then(function (response) {

            if (response.data.result == "success") {
                $scope.modelOffice.showAll = true;
                $scope.allOffices = response.data;

                FactoryOffice.modelOffice.listOfficeByOrgId.length = 0;
                FactoryOffice.modelOffice.orgId = FactoryOrgId.organization.id;
                FactoryOffice.modelOffice.listOfficeByOrgId.push($scope.allOffices.data);
                FactoryOffice.modelOffice.resultMessage = response.data.result;

                $scope.clearFormAddress();

            } else {
                //$scope.resultMessage = response.data.error;//"Filter Offices Data Error!";
                FactoryOffice.modelOffice.resultMessage = response.data.error;
                // $scope.showAllOrgId = false;
            }
        }, function (response) {
            FactoryOffice.modelOffice.resultMessage = response.data.error;
            // $scope.showAllOrgId = false;
        });

    }
});

app.controller('postOfficeControllerUpdate', function ($scope, $http, $location, FactoryOrgId, FactoryOffice) {

    $scope.model = FactoryOrgId.organization;
    $scope.modelOffice = FactoryOffice.modelOffice;
    $scope.modelOffice.showAll = FactoryOffice.modelOffice.showAll;

    $scope.postOfficeUpdate = function () {

        var url = $location.absUrl() + "/api/office/update";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        var data = {
            id: $scope.office.id,
            name: $scope.name,
            address: $scope.address,
            phone: $scope.phone,
            isActive: $scope.isActive,
            orgId: FactoryOrgId.organization.id
        };

        $http.post(url, data, config).then(function (response) {

            if (response.data.result == "success") {
                /* var list = response.data.data;
                 $scope.setView($scope.id, list.name, list.address, list.phone, list.active);
                 FactoryOffice.updateOfficeData($scope.office.id, list.name, list.address, list.phone, list.active);*/

                FactoryOffice.modelOffice.resultMessage = response.data.result;
                //FactoryOffice.modelOffice.showAll = true;
            } else {
                //$scope.getResultMessage = "Organization Data Error!";
                FactoryOffice.modelOffice.resultMessage = response.data.error;
                //FactoryOffice.modelOffice.showAll = false;

            }

        }, function (response) {

            FactoryOffice.modelOffice.showAll = false;
            FactoryOffice.modelOffice.resultMessage = response.data.error;
            //$scope.getResultMessage = "Fail!";
        });

    }
});

app.controller('postOfficeControllerDelete', function ($scope, $http, $location, FactoryOffice, FactoryOrgId) {
    $scope.office = FactoryOffice.office;
    $scope.showAll = true;
    //$scope.id = FactoryOffice.office.id;

    $scope.deleteOfficeById = function () {
        // $scope.setView($scope.id, list.name, list.address, list.phone, list.active);
        var url = $location.absUrl() + "/api/office/delete";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }

        var data = {
            id: FactoryOffice.office.id//$scope.id
        };

        $http.post(url, data, config).then(function (response) {
            if (response.data.result == "success") {
                FactoryOffice.modelOffice.resultMessage = response.data.result;
                $scope.setView('', '', '', '', '');
                FactoryOrgId.setOrgId('');
            } else {
                FactoryOffice.modelOffice.resultMessage = response.data.error;
            }
        }, function (response) {
            //$scope.postResultMessage = "Fail!";
            FactoryOffice.modelOffice.resultMessage = response.data.error;
        });

    }

});

app.controller('postOfficeControllerSave', function ($scope, $http, $location, FactoryOffice, FactoryOrgId) {
    $scope.model = FactoryOrgId.organization;
    $scope.modelOffice = FactoryOffice.modelOffice;

    $scope.postOfficeSave = function () {

        var url = $location.absUrl() + "/api/office/save";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        var data = {
            name: $scope.name,
            address: $scope.address,
            phone: $scope.phone,
            isActive: $scope.isActive,
            orgId: FactoryOrgId.organization.id
        };
        $http.post(url, data, config).then(function (response) {
            if (response.data.result == "success") {
                var list = response.data.data;
                // $scope.setView('', list.name, list.address, list.phone, list.active);
                $scope.setView('', $scope.name, $scope.address, $scope.phone, $scope.isActive);
                FactoryOffice.updateOfficeData('', $scope.name, $scope.address, $scope.phone, $scope.isActive);
                FactoryOffice.modelOffice.resultMessage = response.data.result;
                //$scope.setView('', '', '', '', '');
                //FactoryOrgId.setOrgId('');
            } else {
                FactoryOffice.modelOffice.resultMessage = response.data.error;
            }
        }, function (response) {
            //$scope.postResultMessage = "Fail!";
            FactoryOffice.modelOffice.resultMessage = response.data.error;
        });

    }
});

app.controller('officeController', function ($scope, $http, $location, FactoryOrgId, FactoryOffice) {
    $scope.model = FactoryOrgId.organization;
    $scope.modelOffice = FactoryOffice.modelOffice;
    $scope.office = FactoryOffice.office;
    $scope.address = FactoryOffice.office.address;

    $scope.setView = function (id, name, address, phone, isActive) {
        $scope.office.id = id;
        $scope.name = name;
        $scope.address = address;
        $scope.phone = phone;
        $scope.isActive = isActive;
    }
    $scope.clearFormAddress = function () {
        $scope.address = "";
    }
    $scope.cleanUp = function () {
        $scope.setView('', '', '', '', '');
        FactoryOrgId.setOrgId();

    }
});

app.controller('userController', function ($scope, $http, $location, $filter, FactoryOrgId, FactoryOffice, FactoryUser, FactoryCountry) {
    $scope.model = FactoryOrgId.organization;
    $scope.modelOffice = FactoryOffice.modelOffice;
    $scope.office = FactoryOffice.office;
    $scope.modelUser = FactoryUser.modelUser;
    $scope.allUsers = FactoryUser.allUsers;
    $scope.selected = FactoryUser.modelUser.selected;
    $scope.user = FactoryUser.user;
    $scope.docId = FactoryUser.modelUser.docId;
    $scope.setView = function (id, firstName, secondName, middleName, possition, docCode, docName, citizenShipCode, citizenShipName, phone, docDate, docNumber, isIdentified, officeId, docId) {
        $scope.user.id = id;
        $scope.firstName = firstName;
        $scope.secondName = secondName;
        $scope.middleName = middleName;
        $scope.possition = possition;
        $scope.docCode = docCode;
        $scope.docName = docName;
        $scope.citizenShipCode = citizenShipCode;
        $scope.citizenShipName = citizenShipName;
        $scope.docNumber = docNumber;
        $scope.docDate = docDate;
        $scope.phone = phone;
        $scope.isIdentified = isIdentified;
        $scope.officeId = officeId;
        $scope.docId = docId;
    }

    $scope.changeSelectedCountryInDropList = function () {
        console.log('changeSelectedCountryInDropList begin ----------------------------------');
        FactoryUser.modelUser.countryId = FactoryUser.modelUser.selected.id;
        console.log('FactoryUser.modelUser.countryId ', FactoryUser.modelUser.countryId);
        console.log('FactoryUser.modelUser.selected', FactoryUser.modelUser.selected);
        console.log(' changeSelectedCountryInDropList end ----------------------------');
    }


});

app.controller('postUserControllerListbyOfficeId', function ($scope, $http, $location, FactoryUser, FactoryOffice) {
    //$scope.model = FactoryOrgId.organization;
    $scope.office = FactoryOffice.office;
    $scope.modelOffice = FactoryOffice.modelOffice;
    $scope.modelUser = FactoryUser.modelUser;
    $scope.modelOffice.showAll = false;

    $scope.hideListByOfficeId = function () {
        $scope.modelUser.showAll = false;

    }

    $scope.postUserListByOfficeId = function () {
        var url = $location.absUrl() + "/api/user/list";
        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        var data = {
            officeId: FactoryOffice.office.id,
            firstName: $scope.firstName,
            middleName: $scope.middleName,
            secondName: $scope.secondName,
            possition: $scope.possition,
            docCode: $scope.docCode,
            citizenShipCode: $scope.citizenShipCode
        };
        $http.post(url, data, config).then(function (response) {

            if (response.data.result == "success") {
                $scope.modelUser.showAll = true;
                $scope.modelUser.resultMessage = response.data.result;
                $scope.modelUser.officeId = FactoryOffice.office.id;
                $scope.allUsers = response.data.data;
                FactoryUser.modelUser.listUserByOfficeId = response.data.data;
                /* FactoryUser.modelUser.listOfficeByOfficeId.length = 0;
                 FactoryUser.modelUser.officeId =
                 FactoryUser.modelUser.listUserByOfficeId.push($scope.allUsers.data);
                 FactoryUser.modelUser.resultMessage = response.data.result;*/

                //$scope.clearFormAddress();

            } else {
                //$scope.resultMessage = response.data.error;//"Filter Users Data Error!";
                FactoryUser.modelUser.resultMessage = response.data.error;
                // $scope.showAllOrgId = false;
            }
        }, function (response) {
            FactoryUser.modelUser.resultMessage = response.data.error;
            // $scope.showAllOrgId = false;
        });

    }
});

app.controller('getUserControllerGetById', function ($scope, $timeout, $http, $location, $filter, FactoryOffice, FactoryUser, FactoryCountry) {
    $scope.date = new Date();

    $scope.getUserById = function () {
        var url = $location.absUrl() + "/api/user/" + $scope.user.id;
        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }

        $http.get(url, config).then(function (response) {

            if (response.data.result == "success") {
                $scope.date = new Date();
                var list = response.data.data;
                //$console.log('getUserById start =====================================');
                FactoryUser.user.docDate = $filter('date')(list.docDate, "yyyy-MM-dd");
                //console.log('filterDocDate', filterDocDate);
                console.log('filterDocDate', FactoryUser.user.docDate);
                console.log(list);

                $scope.setView($scope.user.id, list.firstName, list.secondName, list.middleName, list.possition, list.docCode, list.docName, list.citizenshipCode, list.citizenshipName, list.phone, new Date(FactoryUser.user.docDate), list.docNumber, list.identified);
                $scope.userForm.docDate.$setViewValue(new Date(FactoryUser.user.docDate).toISOString().split("T")[0]);
                $scope.userForm.docDate.$render();
                console.log('filter DocDate ', FactoryUser.user.docDate);

                FactoryUser.modelUser.resultMessage = response.data.result;
                FactoryUser.modelUser.countryId = list.citizenshipId;
                FactoryUser.modelUser.docId = list.docId;
                console.log('list.docId ', list.docId);
                console.log('modelUser.countryId ', FactoryUser.modelUser.countryId);
                console.log('modelUser.docId ', FactoryUser.modelUser.docId);
                console.log('before changeSelectedCountryInDropList FactoryUser.modelUser.selected', FactoryUser.modelUser.selected);
                // $scope.changeSelectedCountryInDropList();
                FactoryUser.modelUser.selected.id = list.citizenshipId;
                FactoryUser.modelUser.selected.docs[0].id = list.docId;

                FactoryOffice.setOfficeId(list.officeId);

                console.log('after FactoryOffice.setOfficeId', FactoryOffice.office);
                console.log('list.OfficeId', list.officeId);
                console.log('list', list);

                console.log('after changeSelectedCountryInDropList FactoryUser.modelUser.selected', FactoryUser.modelUser.selected);
                console.log('FactoryUser.country ', FactoryUser.country);
                console.log('getUserById end ==============================================');
                //$scope.postUserControllerAllCountries.postUserAllCountries();
            } else {
                //$scope.getResultMessage = "Offices Data Error!";
                FactoryUser.modelUser.resultMessage = response.data.error;
                $scope.setView('', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '');
            }

        }, function (response) {
            //$scope.getResultMessage = "Fail!";
            FactoryUser.modelUser.resultMessage = response.data.error;
            $scope.setView('', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '');
        });
    };
    app.directive('enter', function() { return function(scope, element, attrs) { element.bind("keydown keypress", function(event) { if(event.which === 13) {$scope=getUserById() } }); };
    });
});

app.controller('postUserControllerUpdate', function ($scope, $http, $location, FactoryUser, FactoryOffice) {

    $scope.modelUser = FactoryUser.modelUser;
    $scope.modelUser.showAll = FactoryUser.modelUser.showAll;

    $scope.postUserUpdate = function () {
        console.log("user update work begin ---------------------");
        // console.log("$scope.docDate " + $scope.docDate);
        console.log("docId:" + FactoryUser.modelUser.selected.docs[0].id);
        console.log("docCode:" + FactoryUser.modelUser.selected.docs[0].code);
        console.log("docName:" + FactoryUser.modelUser.selected.docs[0].name);
        console.log("current DocID:" + FactoryUser.modelUser.currentDocId);
        console.log("user update work end ---------------------");

        var url = $location.absUrl() + "/api/user/update";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        var data = {
            id: FactoryUser.user.id,
            firstName: $scope.firstName,
            secondName: $scope.secondName,
            middleName: $scope.middleName,
            possition: $scope.possition,
            docId: FactoryUser.modelUser.selected.docs[0].id,
            docCode: FactoryUser.modelUser.selected.docs[0].code,
            docName: FactoryUser.modelUser.selected.docs[0].name,
            citizenshipId: FactoryUser.modelUser.countryId,
            citizenshipCode: $scope.citizenShipCode,
            citizenshipName: $scope.citizenShipName,
            docNumber: $scope.docNumber,
            docDate: $scope.docDate,
            // docDate: $filter('date')($scope.docDate, "yyyy-MM-dd"),
            phone: $scope.phone,
            isIdentified: $scope.isIdentified,
            officeId: $scope.office.id
        };

        $http.post(url, data, config).then(function (response) {

            if (response.data.result == "success") {
                FactoryUser.modelUser.resultMessage = response.data.result;
                console.log('filter DocDate', $scope.docDate);
                FactoryOffice.modelOffice.showAll = true;
            } else {
                //$scope.getResultMessage = "Organization Data Error!";
                FactoryUser.modelUser.resultMessage = response.data.error;
                FactoryOffice.modelUser.showAll = false;

            }

        }, function (response) {

            FactoryUser.modelUser.showAll = false;
            FactoryUser.modelUser.resultMessage = response.data.error;
            //$scope.getResultMessage = "Fail!";
        });

    }
});

app.controller('postUserControllerDelete', function ($scope, $http, $location, FactoryUser, FactoryOrgId) {
    $scope.office = FactoryUser.office;
    $scope.showAll = true;
    //$scope.id = FactoryOffice.office.id;

    $scope.deleteUserById = function () {
        var url = $location.absUrl() + "/api/user/delete";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }

        var data = {
            id: FactoryUser.user.id//$scope.id
        };

        $http.post(url, data, config).then(function (response) {
            if (response.data.result == "success") {
                FactoryUser.modelUser.resultMessage = response.data.result;

                $scope.setView('', '', '', '', '', '', '', '', '', '', '', '', '');
                $scope.userForm.docDate.$setPristine();
                FactoryOrgId.setOfficeId('');
            } else {
                FactoryUser.modelUser.resultMessage = response.data.error;
            }
        }, function (response) {
            //$scope.postResultMessage = "Fail!";
            FactoryUser.modelUser.resultMessage = response.data.error;
        });

    }

});

app.controller('postUserControllerSave', function ($scope, $http, $location, FactoryOffice, FactoryUser) {
    $scope.modelUser = FactoryUser.user;
    $scope.modelOffice = FactoryOffice.modelOffice;

    $scope.postUserSave = function () {

        var url = $location.absUrl() + "/api/user/save";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        var data = {
            // id: FactoryUser.user.id,
            firstName: $scope.firstName,
            secondName: $scope.secondName,
            middleName: $scope.middleName,
            possition: $scope.possition,
            docId: FactoryUser.modelUser.selected.docs[0].id,
            docCode: FactoryUser.modelUser.selected.docs[0].code,
            docName: FactoryUser.modelUser.selected.docs[0].name,
            citizenshipId: FactoryUser.modelUser.countryId,
            citizenshipCode: $scope.citizenShipCode,
            citizenshipName: $scope.citizenShipName,
            docNumber: $scope.docNumber,
            docDate: $scope.docDate,
            // docDate: $filter('date')($scope.docDate, "yyyy-MM-dd"),
            phone: $scope.phone,
            isIdentified: $scope.isIdentified,
            officeId: $scope.office.id
        };
        $http.post(url, data, config).then(function (response) {
            if (response.data.result == "success") {
                var list = response.data.data;
                // $scope.setView('', list.name, list.address, list.phone, list.active);
                /*$scope.setView('', $scope.name, $scope.address, $scope.phone, $scope.isActive);
                FactoryUser.updateUserData('', $scope.name, $scope.address, $scope.phone, $scope.isActive);*/
                FactoryUser.modelUser.resultMessage = response.data.result;
                //$scope.setView('', '', '', '', '');
                //FactoryOrgId.setOrgId('');
            } else {
                FactoryUser.modelUser.resultMessage = response.data.error;
            }
        }, function (response) {
            //$scope.postResultMessage = "Fail!";
            FactoryUser.modelUser.resultMessage = response.data.error;
        });

    }
});

app.controller('getUserControllerLoadDocs', function ($scope, $http, $location, FactoryUser, FactoryOffice, FactoryDoc, FactoryCountry) {
    //$scope.model = FactoryOrgId.organization;
    $scope.office = FactoryOffice.office;
    $scope.modelOffice = FactoryOffice.modelOffice;
    $scope.modelUser = FactoryUser.modelUser;
    $scope.modelOffice.showAll = false;
    $scope.selected = FactoryUser.selected;
    /* $scope.selected.id = FactoryUser.modelUser.selected.id;
     $scope.selected.name = FactoryUser.selected.name;*/
    $scope.countryId = FactoryCountry.countryId;
    $scope.doc = FactoryDoc.doc;

    $scope.getCountryId = function () {
        $scope.selected = FactoryUser.selected;
        $scope.selected.id = FactoryCountry.countryId;
        //alert(FactoryCountry.countryId);
    }

    $scope.hideListByOfficeId = function () {
        $scope.modelUser.showAll = false;

    }

    $scope.getUserLoadDocs = function () {
        var url = $location.absUrl() + "/api/loadDocs/" + FactoryUser.modelUser.countryId;

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        /*var data = {
            countryId: FactoryUser.selected.id,
            /!*id: $scope.id,
            code: $scope.code,
            name: $scope.name*!/

        };*/
        $http.get(url, config).then(function (response) {

            if (response.data.result == "success") {
                $scope.modelUser.showAll = true;
                $scope.modelUser.resultMessage = response.data.result;

                $scope.allDocs = response.data.data;
                for (i = 0; i < response.data.data.length; i++) {
                    FactoryDoc.doc[i] = response.data.data[i];
                }
            } else {
                //$scope.resultMessage = response.data.error;//"Filter Users Data Error!";
                FactoryUser.modelUser.resultMessage = response.data.error;
                // $scope.showAllOrgId = false;
            }
        }, function (response) {
            FactoryUser.modelUser.resultMessage = response.data.error;
            // $scope.showAllOrgId = false;
        });

    }
});

app.controller('postUserControllerAllDocs', function ($scope, $http, $location, FactoryUser, FactoryOffice, FactoryDoc) {
    //$scope.model = FactoryOrgId.organization;
    $scope.office = FactoryOffice.office;
    $scope.modelOffice = FactoryOffice.modelOffice;
    $scope.modelUser = FactoryUser.modelUser;
    $scope.modelOffice.showAll = false;
    $scope.fruit = FactoryDoc.fruit;
    $scope.doc = FactoryDoc.doc;
    //$scope.countryId = FactoryDoc.countryId;
    $scope.countryId = FactoryUser.modelUser.countryId;
    $scope.country = FactoryUser.modelUser.countryName;

    $scope.hideListByOfficeId = function () {
        $scope.modelUser.showAll = false;

    }

    $scope.postUserAllDocs = function () {
        var url = $location.absUrl() + "/api/docs";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        var data = {
            id: $scope.id,
            code: $scope.code,
            name: $scope.name

        };
        $http.post(url, data, config).then(function (response) {

            if (response.data.result == "success") {
                $scope.modelUser.showAll = true;
                $scope.modelUser.resultMessage = response.data.result;

                $scope.allCounries = response.data.data;
                //FactoryDoc.doc.push(response.data.data);
                FactoryDoc.doc.length = 0;
                for (i = 0; i < $scope.allCounries.length; i++) {
                    FactoryDoc.doc[i] = response.data.data[i];
                }


            } else {
                //$scope.resultMessage = response.data.error;//"Filter Users Data Error!";
                FactoryUser.modelUser.resultMessage = response.data.error;
                // $scope.showAllOrgId = false;
            }
        }, function (response) {
            FactoryUser.modelUser.resultMessage = response.data.error;
            // $scope.showAllOrgId = false;
        });

    }
});

app.controller('postUserControllerAllCountries', function ($scope, $http, $location, FactoryUser, FactoryOffice, FactoryCountry) {
    $scope.office = FactoryOffice.office;
    $scope.modelOffice = FactoryOffice.modelOffice;
    $scope.modelUser = FactoryUser.modelUser;
    $scope.modelOffice.showAll = false;
    // $scope.country = FactoryCountry.country;
    $scope.country = FactoryUser.country;
    $scope.country.doc = FactoryUser.country.doc;
    $scope.countryId = FactoryUser.countryId;

    $scope.postUserAllCountries = function () {
        console.log('postUserAllCountries start start start start start start start start start start start start start start start start start');
        console.log('FactoryUser.modelUser.countryId', FactoryUser.modelUser.countryId);

        var url = $location.absUrl() + "/api/countries";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        var data = {
            id: $scope.id,
            code: $scope.code,
            name: $scope.name
        };
        $http.post(url, data, config).then(function (response) {

            if (response.data.result == "success") {
                $scope.modelUser.showAll = true;
                $scope.modelUser.resultMessage = response.data.result;

                $scope.allCountries = response.data.data;
                for (i = 0; i < response.data.data.length; i++) {
                    FactoryUser.country[i] = response.data.data[i];
                    // FactoryUser.country[i].docs.push(response.data.data[i].docs);

                    for (j = 0; j < response.data.data[i].docs.length; j++) {
                        FactoryUser.country[i].docs[j] = response.data.data[i].docs[j];
                    }

                }
                console.log('FactoryUser.country ', FactoryUser.country);
                console.log('$scope.selected ', $scope.selected);
                console.log('FactoryUser.modelUser.selected ', FactoryUser.modelUser.selected);
                console.log('FactoryUser.modelUser.currenDocId ', FactoryUser.modelUser.currentDocId);
                // console.log('FFactoryUser.modelUser.selected.docs[modelUser.currentDocId] ', FactoryUser.modelUser.selected.docs[0]);
                console.log('postUserAllCountries end end end end end end end end end end end end end end ');
            } else {
                //$scope.resultMessage = response.data.error;//"Filter Users Data Error!";
                FactoryUser.modelUser.resultMessage = response.data.error;
                // $scope.showAllOrgId = false;
            }
        }, function (response) {
            FactoryUser.modelUser.resultMessage = response.data.error;
            // $scope.showAllOrgId = false;
        });

    }
});

app.controller('postRegisterControllerAdd', function ($scope, $http, $location, FactoryReg) {

    $scope.modelReg = FactoryReg.modelReg;

    $scope.postRegisterAdd = function () {
        console.log('postRegAdd start start start start start start start start start start start start start');

        var url = $location.absUrl() + "/api/register";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        var data = {
            login: $scope.login,
            password: $scope.password,
            name: $scope.name
        };
        $http.post(url, data, config).then(function (response) {
            console.log("response.data.result " + response.data.result);
            if (response.data.result == "success") {
                $scope.modelReg.showAll = true;
                $scope.modelReg.resultMessage = response.data.result;

                console.log('postUserAllCountries end end end end end end end end end end end end end end ');
            } else {
                //$scope.resultMessage = response.data.error;//"Filter Users Data Error!";
                FactoryReg.modelReg.resultMessage = response.data.error;
                // $scope.showAllOrgId = false;
            }
        }, function (response) {
            FactoryReg.modelReg.resultMessage = response.data.error;
            // $scope.showAllOrgId = false;
        });

    }
});

app.controller('getRegisterControllerActivation', function ($scope, $http, $location, FactoryReg) {

    $scope.modelReg = FactoryReg.modelReg;

    $scope.getRegisterActivation = function () {
        console.log('getRegActivate start start start start start start start start start start start start start');

        var url = $location.absUrl() + "/api/activation";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            },
            params: {'code': $scope.code}
        }

        $http.get(url, config).then(function (response) {
            console.log("code  " + $scope.code);
            console.log("response.data.result " + response.data.result);
            if (response.data.result == "success") {
                $scope.modelReg.showAll = true;
                $scope.modelReg.resultMessage = response.data.result;
                console.log("code 2 " + FactoryReg.modelReg.code);
                console.log('postUserAllCountries end end end end end end end end end end end end end end ');
            } else {
                //$scope.resultMessage = response.data.error;//"Filter Users Data Error!";
                FactoryReg.modelReg.resultMessage = response.data.error;
                // $scope.showAllOrgId = false;
            }
        }, function (response) {
            FactoryReg.modelReg.resultMessage = response.data.error;
            // $scope.showAllOrgId = false;
        });

    }
});

app.controller('postRegisterControllerLogin', function ($scope, $http, $location, FactoryReg) {

    $scope.modelReg = FactoryReg.modelReg;

    // $scope.modelReg.showAll = FactoryReg.modelReg.showAll;

    $scope.postRegisterLogin = function () {
        console.log('postRegLogin start start start start start start start start start start start start start');

        var url = $location.absUrl() + "/api/login";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        var data = {
            login: $scope.login,
            password: $scope.password,
            name: $scope.name
        };
        $http.post(url, data, config).then(function (response) {
            console.log("response.data.result " + response.data.result);
// >>>>>>> d911fb6209b29b5d5f2d64927e11bf796746b44b
            if (response.data.result == "success") {
                $scope.modelReg.showAll = true;
                $scope.modelReg.resultMessage = response.data.result;

                console.log('postUserAllCountries end end end end end end end end end end end end end end ');
            } else {
                //$scope.resultMessage = response.data.error;//"Filter Users Data Error!";
                FactoryReg.modelReg.resultMessage = response.data.error;
                // $scope.showAllOrgId = false;
            }
        }, function (response) {
            FactoryReg.modelReg.resultMessage = response.data.error;
            // $scope.showAllOrgId = false;
        });

    }
});

/*app.controller('getRegisterControllerActivation', function ($scope, $http, $location, FactoryReg) {

    $scope.modelReg = FactoryReg.modelReg;

    //var code =
    //$scope.code;// = FactoryReg.modelReg.code;

    $scope.getRegisterActivation = function () {
        console.log('getRegActivate start start start start start start start start start start start start start');

        var url = $location.absUrl() + "/api/activation";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            },
            params: {'code': $scope.code}


        }

        $http.get(url, config).then(function (response) {
            console.log("code  " + $scope.code);
            console.log("response.data.result " + response.data.result);
            if (response.data.result == "success") {
                $scope.modelReg.showAll = true;
                $scope.modelReg.resultMessage = response.data.result;
console.log("code 2 " + FactoryReg.modelReg.code);
                console.log('postUserAllCountries end end end end end end end end end end end end end end ');
            } else {
                //$scope.resultMessage = response.data.error;//"Filter Users Data Error!";
                FactoryReg.modelReg.resultMessage = response.data.error;
                // $scope.showAllOrgId = false;
            }
        }, function (response) {
            FactoryReg.modelReg.resultMessage = response.data.error;
            // $scope.showAllOrgId = false;
        });

    }
});*/


app.factory('FactoryOrgId', function () {
    return {
        organization: {
            id: ''
        },
        setOrgId: function (id) {
            this.organization.id = id;
        }
    };
});

app.factory('FactoryOffice', function () {
    var name = 'Bob';
    return {

        setName: function (name) {
            name = name;
        },
        getName: function () {
            return this.resultMessage;
        },
        office: {
            id: '',
            name: '',
            address: '',
            phone: '',
            isActive: ''
        },
        modelOffice: {
            resultMessage: '',
            showAll: '',
            orgId: '',
            listOfficeByOrgId: [{}]
        },
        updatelistOfficeByOrgId: function (name, phone, isActive) {
            {
                this.listOfficeByOrgId.name = name,
                    this.listOfficeByOrgId.phone = phone,
                    this.listOfficeByOrgId.isActive = isActive
            }
        },
        updateOfficeData: function (id, name, address, phone, isActive) {
            this.office.id = id;
            this.office.name = name;
            this.office.address = address;
            this.office.phone = phone;
            this.office.isActive = isActive;
        },
        setOfficeId: function (id) {
            this.office.id = id;
        }
    }
});

app.factory('FactoryUser', function () {
    var name = 'Bob';
    return {

        setName: function (name) {
            name = name;
        },
        getName: function () {
            return this.resultMessage;
        },
        user: {
            id: '',
            firstName: '',
            secondName: '',
            middleName: '',
            possition: '',
            docCode: '',
            docName: '',
            citizenshipCode: '',
            citizenshipName: '',
            docNumber: '',
            docDate: '',
            phone: '',
            isIdentified: ''
        },
        modelUser: {
            resultMessage: '',
            showAll: '',
            officeId: '',
            docId: 1,
            country: '',
            countryId: 1,
            currentDocId: 0,
            selected: {
                id: 1,
                code: 643,
                name: 'Российская Федерация',
                docs: [{id: 1, code: 21, name: 'Паспорт гражданина Российской Федерации'}]
            }
        },
        country: [{
            id: '',
            code: '',
            name: '',
            docs: [{id: '', code: '', name: ''}]
        }],


       /* updatelistUserByOfficeId: function (name, phone, isIdentified) {
            {
                this.listUserByOfficeId.firstName = name,
                    this.listUserByOfficeId.phone = phone,
                    this.listUserByOfficeId.isIdentified = isIdentified
            }
        },*/

        /*setId: function () {
            this.user.id = $scope.id;
        },
        setCountry: function (country) {
            this.user.modelUser.countryName = country;

        },
        changeSelected: function (id, code, name) {
            this.country[id - 1].id = id;
            this.country[id - 1].code = code;
            this.country[id - 1].name = name;
        },
        changeSelectedId: function () {
            this.country[0].id = 1;
        },
        setCurrentDocId: function (id) {
            this.modelUser.currentDocId = id;
        },
        getCurrentDocId: function () {
            return this.modelUser.currentDocId;
        },
        changeSelectedCountry: function (country) {
            this.modelUser.selected = country;
        }*/
    }
});

app.factory('FactoryDoc', function () {
    return {
        doc: [{
            id: '',
            code: '',
            name: '',
            countries: [{id: '', code: '', name: ''}]
        }],
        //countryId: '',
        fruit: [{
            id: '',
            name: ''
        }],
        setDocId: function (id) {
            this.doc.id = id;
        }
    };
});

app.factory('FactoryCountry', function () {
    return {
        country: [{
            id: '',
            code: '',
            name: ''
        }],
        countryId: 1,

        setCountryId: function (id) {
            this.countryId = id;
        },
        changeSelected: function (id, code, name) {
            this.selected.id = id;
            this.selected.code = code;
            this.selected.name = name;
        },
        getSelectedId: function () {
            return this.selected.id;
        },
        changeSelected: function (id) {
            this.selected.id = id;
        },
    };
});

app.factory('FactoryReg', function() {
    return {
        modelReg: {
            resultMessage: '',
            showAll: false,
            code: ''
        }
    };
});

