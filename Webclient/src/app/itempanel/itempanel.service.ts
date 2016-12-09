

export class ItempanelService {
  itemMockup = [
    {name: "Kinderriegel", value: "0.25"},
    {name: "Kaffee", value: "0.25"},
    {name: "Bier", value: "0.80"}
  ];

  public transaction = {};

  getItems(): {}[] {
    return this.itemMockup;
  }

  customValue(itemValue: string): void {

  }

  processTransaction() {
    console.log("New Transaction: " + "Person: " + this.transaction["name"] + " Value: " + this.transaction["value"]);
  }
}
