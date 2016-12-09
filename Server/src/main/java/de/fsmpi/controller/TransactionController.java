package de.fsmpi.controller;

import com.google.gson.Gson;
import de.fsmpi.data.*;
import de.fsmpi.services.CreditCalculationService;
import de.fsmpi.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Date;

/**
 * Created by Marcel Fraas on 08.09.16.
 */

@Controller
public class TransactionController extends TextWebSocketHandler {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CreditCalculationService creditCalculationService;


    @Transactional
    @MessageMapping("/transact")
    @SendTo("/sockets/data")
    public String transact(TransactionDetails transactionDetails) throws Exception {
        Person person = personRepository.findByPersonName(transactionDetails.getPerson());
        Product product = productRepository.findByProductName(transactionDetails.getProduct());
        transactionRepository.save(transactionRepository.save(new Transaction(new Date(1472506434), product, person)));
        creditCalculationService.calculate();

        Gson gson = new Gson();
        System.out.println("JSON: " + gson.toJson(creditCalculationService.getCredits()));

        //getTopTenData();

        return gson.toJson(creditCalculationService.getCredits());
    }

    @SendTo("/sockets/topten")
    public String getTopTenData() throws Exception {
        Gson gson = new Gson();
        MapUtil mu = new MapUtil();
        String re = gson.toJson(mu.sortByValue(creditCalculationService.getCredits()));
        return re;
    }
}
