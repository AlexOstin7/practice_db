var app = angular.module('app', []);

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

app.controller('postOrganizationsControllerUpdate', function ($scope, $http, $location, FactoryOrgId) {
    $scope.model = FactoryOrgId.organization;
    $scope.showOrganization = false;

    $scope.getOrganization = function () {
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
    $scope.submitForm = function () {
        var url = $location.absUrl() + "/api/organization/update";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }

        var data = {
            id: $scope.organizationId,
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
            console.log(response.data);
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
        var url = $location.absUrl() + "/api/organization/lists";

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
                $scope.getResultMessage = "get All Organizations Data Error!";
            }
        }, function (response) {
            $scope.showAllOrganizations = false;
            $scope.postResultMessage = "Fail!";
        });

        $scope.name = "";
        $scope.inn = "";
        $scope.isActive = "";
    }
});

app.controller('getAllOrganizationsControllerBrief', function ($scope, $http, $location) {

    $scope.showAllOrganizations = false;

    $scope.getAllOrganizations = function () {
        var url = $location.absUrl() + "/api/organization"; // "findall";

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
        var url = $location.absUrl() + "/api/organization"; // "findall";

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
        var url = $location.absUrl() + "/api/office"; // "findall";

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
    $scope.show = false;

    $scope.getOfficeById = function () {
        var url = $location.absUrl() + "/api/office/" + $scope.office.id;

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }

        $http.get(url, config).then(function (response) {

            if (response.data.result == "success") {
                $scope.show = true;
                var list = response.data;
                $scope.office.name = list.data.name;
                $scope.office.address = list.data.address;
                $scope.office.phone = list.data.phone;
                $scope.office.isActive = list.data.active;

            } else {
                $scope.getResultMessage = "Offices Data Error!";
            }

        }, function (response) {
            $scope.getResultMessage = "Fail!";
        });
    }
});

app.controller('postOfficeControllerListbyOrgId', function ($scope, $http, $location, FactoryOrgId, FactoryOffice) {
    $scope.model = FactoryOrgId.organization;
    $scope.office = FactoryOffice.office;
    //$scope.showAll = FactoryOffice.showAll;
    $scope.modelOffice = FactoryOffice.modelOffice;
    $scope.modelOffice.showAll = false;
    //$scope.listOfficeByOrgId = FactoryOffice.listOfficeByOrgId;
    //$scope.listOfficeByOrgId = FactoryOffice.listOfficeByOrgId;
    //$scope.listOfficeByOrgId.name = "FFF";
    $scope.getOfficeListByOrgId = function () {
        var url = $location.absUrl() + "/api/office/list";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        var data = {
            orgId: FactoryOrgId.organization.id,
            name: $scope.office.name,
            phone: $scope.office.phone,
            isActive: $scope.office.isActive
        };
        $http.post(url, data, config).then(function (response) {
            $scope.resultMessage = response.data.result;
            //$scope.modelOffice.showAll = false;
            if (response.data.result == "success") {
                $scope.allOffices = response.data;
                $scope.name = response.data.data.name;
                //FactoryOffice.listOfficeByOrgId.name = "HHH";
                //$scope.listOfficeByOrgId =  response.data;
                FactoryOffice.modelOffice.listOfficeByOrgId.length = 0;
                FactoryOffice.modelOffice.orgId = FactoryOrgId.organization.id;
                FactoryOffice.modelOffice.listOfficeByOrgId.push($scope.allOffices.data);
                FactoryOffice.modelOffice.showAll = true;
               // $scope.showAll = true;
                //FactoryOffice.updatelistOfficeByOrgId(response.data.data.name, $scope.allOffices.data.phone, $scope.allOffices.data.isActive)
            } else {
                $scope.resultMessage = response.data.error;//"Filter Offices Data Error!";
            }
        }, function (response) {
            $scope.showAll = false;
            $scope.resultMessage = "Fail!";
        });
        $scope.address = "";
    }
});

app.controller('officeController', function ($scope, $http, $location, FactoryOrgId, FactoryOffice) {
    $scope.model = FactoryOrgId.organization;
    $scope.office = FactoryOffice.office;
    //$scope.showAll = false;

    $scope.showAll = FactoryOffice.showAll;
});




app.factory('FactoryOrgId', function () {
    return {
        organization: {
            id: ''
        }
    };
});

app.factory('FactoryOffice', function () {
    return {

        office: {
            id: '',
            name: '',
            address: '',
            phone: '',
            isActive: ''
        },
        modelOffice: {
            showAll: '',
            orgId: '',
            listOfficeByOrgId: [{}]
        },
        updatelistOfficeByOrgId: function (name, phone, isActive) {
            {
                this.listOfficeByOrgId.name =  name,
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
        }
    }
});


