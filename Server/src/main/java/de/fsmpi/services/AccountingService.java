package de.fsmpi.services;

import de.fsmpi.data.Accounting;
import de.fsmpi.data.AccountingRepository;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Marcel Fraas on 29.08.16.
 */
@Service
public class AccountingService {

    @Autowired
    private AccountingRepository accountingRepository;

    @Autowired
    private CreditCalculationService creditCalculationService;

    public void account() {

        System.out.println("******************STARTE ABRECHNUNG******************");

        Map<String, Double> credits = creditCalculationService.calculate();

        if(credits==null){
            System.out.println("Neue Abrechnung nicht notwendig, da keine neuen Transaktionen");
        } else {
            try(
                    InputStream source = getClass().getResourceAsStream("/poi/template.xlsx");
                    ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ) {
                XSSFWorkbook wb = new XSSFWorkbook(source);
                XSSFSheet sheet = wb.getSheetAt(0);


                int i = 0;
                Iterator<Map.Entry<String, Double>> iter = credits.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<String, Double> entry = iter.next();
                    sheet.createRow(i);
                    sheet.getRow(i).createCell(0);
                    sheet.getRow(i).createCell(1);
                    sheet.getRow(i).getCell(0).setCellValue(entry.getKey());
                    sheet.getRow(i).getCell(1).setCellValue(entry.getValue());
                    i++;
                }


                wb.write(bout);

                Accounting a = new Accounting(new Date(), bout.toByteArray());
                accountingRepository.save(a);
            } catch (Exception e) {
                e.printStackTrace();
            }

            creditCalculationService.calculate();
        }

    }

    public void export(long id, String file) {
        Accounting abrechnung = accountingRepository.findOne(id);
        try{
            ByteArrayInputStream in = new ByteArrayInputStream(abrechnung.getTable());
            XSSFWorkbook workbook = new XSSFWorkbook(in);
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
