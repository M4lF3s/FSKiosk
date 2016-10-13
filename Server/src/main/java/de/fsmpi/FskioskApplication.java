package de.fsmpi;

import de.fsmpi.data.*;
import de.fsmpi.services.AccountingService;
import de.fsmpi.services.CreditCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Date;

@SpringBootApplication
public class FskioskApplication {

	public static void main(String[] args) {
		SpringApplication.run(FskioskApplication.class, args);
	}
}


/**
 * This Commandline Runner is for Testing -> should be moved to an Integration Test
 */
@Component
class TestCLR implements CommandLineRunner {

	private TransactionRepository transactionRepository;

	private AccountingService accountingService;

	private CreditCalculationService creditCalculationService;

	private PersonRepository personRepository;

	private ProductRepository productRepository;

	@Autowired
	public TestCLR(
			TransactionRepository transactionRepository,
			AccountingService accountingService,
			CreditCalculationService creditCalculationService,
			ProductRepository productRepository,
			PersonRepository personRepository
	) {
		this.transactionRepository = transactionRepository;
		this.accountingService = accountingService;
		this.creditCalculationService = creditCalculationService;
		this.productRepository = productRepository;
		this.personRepository = personRepository;
	}

	@Override
	public void run(String... strings) throws Exception {

		System.out.println("Initialisiere aktuelle Werte");
		creditCalculationService.calculate();
		accountingService.account();

		System.out.println("Adding Some Transactions");
		Person p = new Person("Marcel");
		Person p2 = new Person("Armin");
		Person p3 = new Person("Marcel");
		Product e = new Product("Kinderriegel", 0.25);
		Product e2 = new Product("Bier", 0.80);
		Product e3 = new Product("Kaffee", 0.25);

		personRepository.save(p);
		personRepository.save(p2);
		personRepository.save(p3);

		productRepository.save(e);
		productRepository.save(e2);
		productRepository.save(e3);

		transactionRepository.save(new Transaction(new Date(1472506434), e, p));
		transactionRepository.save(new Transaction(new Date(1472569536), e2, p2));
		transactionRepository.save(new Transaction(new Date(1472569537), e3, p3));
		accountingService.account();


		//abrechnungService.export(1, "export.xlsx");


	}

}