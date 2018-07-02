var app = angular.module('app', []);//1

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

app.controller('userController', function ($scope, $http, $location, FactoryOrgId, FactoryOffice, FactoryUser) {
    $scope.model = FactoryOrgId.organization;
    $scope.modelOffice = FactoryOffice.modelOffice;
    $scope.office = FactoryOffice.office;
    $scope.modelUser = FactoryUser.modelUser;
    $scope.user = FactoryUser.user;
    $scope.setView = function (id, firstName, secondName, middleName, phone, docDate, docNumber, isIdentified, officeId, docId) {
        $scope.user.id = id;
        $scope.firstName = firstName;
        $scope.secondName = secondName;
        $scope.middleName = middleName;
        $scope.docNumber = docNumber;
        $scope.docDate = docDate;
        $scope.phone = phone;
        $scope.isIdentified = isIdentified;
        $scope.officeId = officeId;
        $scope.docId = docId;
    }

    $scope.cleanUp = function () {
        $scope.setView('', '', '', '', '', '', '', '', '', '');
    }
});

app.controller('postUserControllerListbyOfficeId', function ($scope, $http, $location, FactoryUser, FactoryOffice, FactoryOrgId) {
    $scope.model = FactoryOrgId.organization;
    $scope.office = FactoryOffice.office;
    $scope.modelOffice = FactoryOffice.modelOffice;
    $scope.modelOffice.showAll = false;


    $scope.hideListByOfficeId = function () {
        $scope.modelUser.showAll = false;

    }

    $scope.postUserListByOfficeId = function () {
        var url = $location.absUrl() + "/api/user/list";
//alert('ooo3');

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        var data = {
            officeId: FactoryOffice.office.id,
            firstName: $scope.firstName,
            secondName: $scope.secondName,
            middleName: $scope.middleName,
            possition: $scope.possition,
            docCode: $scope.docCode,
            citezenShipCode: $scope.citizenShipCode
        };
        $http.post(url, data, config).then(function (response) {

            if (response.data.result == "success") {
                $scope.modelOffice.showAll = true;
                $scope.allUsers = response.data;

                FactoryUser.modelUser.listOfficeByOfficeId.length = 0;
                FactoryUser.modelUser.officeId = FactoryOffice.office.id;
                FactoryUser.modelUser.listUserByOfficeId.push($scope.allUsers.data);
                FactoryUser.modelUser.resultMessage = response.data.result;

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
            listUserByOfficeId: [{}]
        },
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
        }
    }
});

