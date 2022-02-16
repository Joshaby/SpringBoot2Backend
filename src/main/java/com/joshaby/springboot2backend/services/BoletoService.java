package com.joshaby.springboot2backend.services;

import com.joshaby.springboot2backend.entities.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void generateDueDate(PagamentoComBoleto pagamento, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        pagamento.setDataPagamento(calendar.getTime());
    }
}
