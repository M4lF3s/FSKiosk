import { Component, OnInit } from '@angular/core';
import { PersonpanelService } from './personpanel.service';
import { ItempanelService } from '../itempanel/itempanel.service';
import { Person } from '../models/person';
import { Configuration } from '../app.constants';

@Component({
  selector: 'app-personpanel',
  templateUrl: './personpanel.component.html',
  styleUrls: ['./personpanel.component.css'],
  providers: [PersonpanelService, ItempanelService, Configuration]
})
export class PersonpanelComponent implements OnInit {

  personPanelService;
  itempanelService;

  public showAddNewPerson = false;

  constructor(personpanelService: PersonpanelService,
              itempanelService: ItempanelService) {
    this.personPanelService = personpanelService;
    this.itempanelService = itempanelService;
  }

  ngOnInit() {

  }

  toggleAddNewPerson() {
    this.showAddNewPerson = !this.showAddNewPerson;
  }

  addNewPerson(name: string) {
    this.personPanelService.addNewPerson(name);
    this.toggleAddNewPerson();
  }

  processPerson(value: string) {
    console.log(value);
    this.itempanelService.transaction["name"] = value;
  }
}
