

export class TransactionService {

  public transaction = {};

  processTransaction() {
    console.log("New Transaction: " + "Person: " + this.transaction["name"] + " Value: " + this.transaction["value"]);
  }

}
