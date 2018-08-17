var app = angular.module('app', []);//17/07/2018

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

app.controller('userController', function ($scope, $http, $location, FactoryOrgId, FactoryOffice, FactoryUser, FactoryCountry) {
    $scope.model = FactoryOrgId.organization;
    $scope.modelOffice = FactoryOffice.modelOffice;
    $scope.office = FactoryOffice.office;
    $scope.modelUser = FactoryUser.modelUser;
    $scope.allUsers = FactoryUser.allUsers;
    $scope.selected = FactoryCountry.selected;
    $scope.user = FactoryUser.user;

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

    $scope.cleanUp = function () {
        $scope.setView('', '', '', '', '', '', '', '', '', '', '', '', '');
    }

    $scope.setCountryId = function () {
        console.log('!!!');
        console.log(FactoryUser.modelUser.countryId);
        FactoryUser.modelUser.countryId = FactoryCountry.selected.id;
//$scope.selected = FactoryCountry.country[1];
        FactoryCountry.setSelected2(FactoryUser.country[1]);
        FactoryCountry.setSelected(1, 112 , 'Республика Беларусь2');
        console.log(FactoryUser.modelUser.countryId);
        console.log('end');
        console.log('fun.seCountryId fCountrr.selected ' , FactoryCountry.selected);

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

app.controller('getUserControllerGetById', function ($scope, $http, $location, FactoryOffice, FactoryUser, FactoryCountry) {
    //$scope.model = FactoryOrgId.organization;
    $scope.office = FactoryOffice.office;
    $scope.modelOffice = FactoryOffice.modelOffice;
    $scope.user = FactoryUser.user;
    $scope.modelUser = FactoryUser.modelUser;
    $scope.getUserById = function () {
        var url = $location.absUrl() + "/api/user/" + $scope.user.id;

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }

        $http.get(url, config).then(function (response) {

            if (response.data.result == "success") {
                var list = response.data.data;

                console.log(list);
                $scope.setView($scope.user.id, list.firstName, list.secondName, list.middleName, list.possition, list.docCode, list.docName, list.citizenshipCode, list.citizenshipName, list.phone, list.docDate, list.docNumber, list.identified);
                FactoryUser.modelUser.resultMessage = response.data.result;

                //FactoryCountry.selected = FactoryCountry.country[1];
                //FactoryCountry.setSelected(FactoryCountry.getSelectedId(), list.citizenshipCode, list.citizenshipName);
                //FactoryUser.setSelected(FactoryCountry.getSelectedId(), list.citizenshipCode, list.citizenshipName);
                //FactoryUser.country.refresh();
               // FactoryCountry.setSelectedId();
// FactoryCountry.setSelected2(FactoryUser.country[1]);
                console.log('getId fCountry.selected', FactoryCountry.selected);
                console.log('getId fUser.country', FactoryUser.country);
                //$scope.postUserControllerAllCountries.postUserAllCountries();
            } else {
                //$scope.getResultMessage = "Offices Data Error!";
                FactoryUser.modelUser.resultMessage = response.data.error;
                $scope.setView('', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '');
                //FactoryOrgId.setOrgId('');
            }

        }, function (response) {
            //$scope.getResultMessage = "Fail!";
            FactoryUser.modelUser.resultMessage = response.data.error;
            $scope.setView('', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '');
            //FactoryOrgId.setOrgId('');
        });
    }
});

app.controller('getUserControllerLoadDocs', function ($scope, $http, $location, FactoryUser, FactoryOffice, FactoryDoc, FactoryCountry) {
    //$scope.model = FactoryOrgId.organization;
    $scope.office = FactoryOffice.office;
    $scope.modelOffice = FactoryOffice.modelOffice;
    $scope.modelUser = FactoryUser.modelUser;
    $scope.modelOffice.showAll = false;
    $scope.selected = FactoryCountry.selected;
    $scope.selected.id = FactoryCountry.selected.id;
    $scope.selected.name = FactoryCountry.selected.name;
    $scope.countryId = FactoryCountry.countryId;
    $scope.doc = FactoryDoc.doc;

    $scope.getCountryId = function () {
        $scope.selected = FactoryCountry.selected;
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
            countryId: FactoryCountry.selected.id,
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
  // $scope.selected = FactoryCountry.selected2;
//Sscope.selected = FactoryUser.country[1];


    $scope.postUserAllCountries = function () {
        console.log(FactoryUser.modelUser.countryId);

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
                       //FactoryUser.modelUser.initDropDown = true;
                    }
                console.log('focus factoryCountry.selected' ,FactoryCountry.selected);
                console.log('fUser.country ' ,FactoryUser.country);
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
        setId: function () {
            this.office.id = $scope.id;
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
            docId: '',
            country: '',
            countryId: 0,
            initDropDown: false,
            listCountry: [{
                id: '',
                code: '',
                name: ''
            }],
            listDoc: [{
                id: '',
                code: '',
                name: ''
            }]
        },
        country: [{
            id: '',
            code: '',
            name: '',
            docs: [{id: '', code: '', name: ''}]
        }],
        updatelistUserByOfficeId: function (name, phone, isIdentified) {
            {
                this.listUserByOfficeId.firstName = name,
                    this.listUserByOfficeId.phone = phone,
                    this.listUserByOfficeId.isIdentified = isIdentified
            }
        },
        updateUserData: function (id, firstName, secondName, middleName, phone, docName, docNumber, isIdentified, officeId, docId) {
            this.user.id = id;
            this.user.firstName = firstName;
            this.user.secondName = secondName;
            this.user.middleName = middleName;
            this.user.phone = phone;
            this.user.docNumber = docNumber;
            this.user.docName = docName;
            this.user.isIdentified = isIdentified;
            this.user.officeId = officeId;
            this.user.docId = docId;
        },
        setId: function () {
            this.user.id = $scope.id;
        },
        setCountry: function (country) {
            this.user.modelUser.countryName = country;

        },
        setSelected: function (id, code, name) {
            this.country[id-1].id = id;
            this.country[id-1].code = code;
            this.country[id-1].name = name;
        },
        setSelectedId: function () {
            this.country[0].id = 1;
        }
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
        selected: {
            id: 1,
            code: 643,
            name: 'Российская Федерация',
            docs: [{id: '', code: '', name: ''}]
        },
        setCountryId: function (id) {
            this.countryId = id;
        },
        setSelected: function (id, code, name) {
            this.selected.id = id;
            this.selected.code = code;
            this.selected.name = name;
        },
        getSelectedId: function () {
            return this.selected.id;
        },
        setSelected2: function (country) {
            this.selected = country;
        }
    };
});

