package de.fsmpi.services;

import de.fsmpi.data.Accounting;
import de.fsmpi.data.AccountingRepository;
import de.fsmpi.data.Transaction;
import de.fsmpi.data.TransactionRepository;
import de.fsmpi.utils.PrettyPrintingMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marcel Fraas on 30.08.16.
 */
@Service
public class CreditCalculationService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountingRepository accountingRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<String, Double> credits = new HashMap<>();

    public Map<String, Double> getCredits() {
        return credits;
    }

    public Map<String, Double> calculate() {
        logger.info("<< Starte Berechnung für aktuellen Stand >>");
        Accounting last = accountingRepository.findLast();
        List<Transaction> transaktionList;
        if(last == null){
            logger.info("[Bisher keine Abrechnungen vorhanden]");
            transaktionList = transactionRepository.findAll();
        } else {
            logger.info("[Letzte Abrechnung war am: " + last.getTimestamp()+"]");
            transaktionList = transactionRepository.find(last.getTimestamp());
        }

        logger.info("Transaktionen seit der letzten Abrechnung:");
        this.credits = new HashMap<>();
        for(Transaction t : transaktionList) {
            logger.info("> " + t);
            Double tmp = this.credits.get(t.getPerson().getPersonName());
            if(tmp == null) {
                this.credits.put(t.getPerson().getPersonName(), t.getProduct().getValue());
            } else {
                tmp += t.getProduct().getValue();
                this.credits.put(t.getPerson().getPersonName(), tmp);
            }
        }

        if(this.credits.isEmpty()){
            logger.info(
                        "[Bisher keine Transaktionen]" + "\n" +
                        "+-----------------+" + "\n" +
                        "| Aktueller Stand |" + "\n" +
                        "+-----------------+" + "\n" +
                        "+-----------------+" + "\n"
            );
            return null;
        } else {
            logger.info("\n" +
                        "+-----------------+" + "\n" +
                        "| Aktueller Stand |" + "\n" +
                        "+-----------------+" + "\n" +
                        new PrettyPrintingMap<>(this.credits) + "\n" +
                        "+-----------------+"
            );
        }

        return this.credits;
    }

    public Map<String, Double> add(String name, Double value) {
        Double tmp = this.credits.get(name);
        this.credits.put(name, tmp+value);
        return this.credits;
    }

    public Map<String, Double> add(Transaction t) {
        Double tmp = this.credits.get(t.getPerson().getPersonName());
        this.credits.put(t.getPerson().getPersonName(), tmp+t.getProduct().getValue());
        return this.credits;
    }

}
