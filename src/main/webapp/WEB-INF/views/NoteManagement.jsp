<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Note Management</title>  
    <style>
      .username.ng-valid {
          background-color: lightgreen;
      }
      .username.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .username.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }
 
      .email.ng-valid {
          background-color: lightgreen;
      }
      .email.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .email.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }
 
    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="noteApp" class="ng-cloak">
      <div class="generic-container" ng-controller="NoteController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Note Management Form </span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.note.id" />
                      <input type="hidden" ng-model="ctrl.note.emailId" />
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="title">Title</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.note.noteTitle" id="title" class="title form-control input-sm" placeholder="Enter title" required ng-maxlength="50"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.title.$error.required">This is a required field</span>
                                      <span ng-show="myForm.title.$error.maxlength">Maximum 50 characters are allowed</span>
                                      <span ng-show="myForm.title.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                         
                       
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="address">Address</label>
                              <div class="col-md-7">
                                  <!-- <input type="TEXTAREA" ng-model="ctrl.note.noteBody" id="address" class="form-control input-sm" placeholder="Enter Note Body. [This field is validation free]"/> -->
                                  <textarea rows="6" cols="100" name="comment" ng-model="ctrl.note.noteBody" class="form-control input-sm" placeholder="Enter Note Body. [This field is validation free]" name="Note Body"></textarea>
                              </div>
                          </div>
                      </div>
 
 
                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.note.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Notes </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>Title</th>
                              <th>Body</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="n in ctrl.notes">
                              <td><span ng-bind="n.id"></span></td>
                              <td><span ng-bind="n.noteTitle"></span></td>
                              <td><span ng-bind="n.noteBody"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(n.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(n.id)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
       
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-resource.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/note_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/note_controller.js' />"></script>
  </body>
</html>