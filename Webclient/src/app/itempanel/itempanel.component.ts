import { Component, OnInit } from '@angular/core';
import { ItempanelService } from './itempanel.service';

@Component({
  selector: 'app-itempanel',
  templateUrl: './itempanel.component.html',
  styleUrls: ['./itempanel.component.css'],
  providers: [ItempanelService]
})
export class ItempanelComponent implements OnInit {

  items;

  itemPanelService;
  transactionService;

  public showAddNewItem = false;

  constructor(itempanelService: ItempanelService) {
    this.itemPanelService = itempanelService;
  }

  ngOnInit() {
    this.items = this.itemPanelService.getItems();
  }

  toggleAddNewItem() {
    this.showAddNewItem = !this.showAddNewItem;
  }

  processItem(value: string) {
    this.itemPanelService.transaction["value"] = value;
    this.itemPanelService.processTransaction();
  }

}
