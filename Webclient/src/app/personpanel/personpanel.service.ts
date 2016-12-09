import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/add/operator/map'
import { Observable } from 'rxjs/Observable';
import { Person } from '../models/person';

var SockJS = require('sockjs-client');
var Stomp = require('stompjs');

@Injectable()
export class PersonpanelService {
  stompClient: any;

  constructor() {
    var that = this;
    var socket = new SockJS('http://127.0.0.1:8080/endpoints');
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect({}, function (frame) {
      console.log('Connected: ' + frame);
      that.stompClient.subscribe('/sockets/data', function (greeting) {
        console.log(JSON.parse(greeting.body).content);
      });
    }, function (err) {
      console.log('err', err);
    });
  }

  personMockup = [
    {name: "Marcel", value: "1.23"},
    {name: "Adal", value: "4.56"},
    {name: "Jan", value: "7.89"}
  ];

  getPersons(): {}[] {
    return this.personMockup;
  }

  addNewPerson(personName: string): void {
    this.personMockup.push(
      {name: personName, value: "0.00"}
    )
  }
}
