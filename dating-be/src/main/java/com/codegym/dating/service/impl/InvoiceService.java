package com.codegym.dating.service.impl;

import com.codegym.dating.model.Invoice;
import com.codegym.dating.model.User;
import com.codegym.dating.repository.IInvoiceRepository;
import com.codegym.dating.service.IInvoiceService;
import com.codegym.dating.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class InvoiceService implements IInvoiceService {
    @Autowired
    private IInvoiceRepository iInvoiceRepository;
    @Autowired
    private IUserService iUserService;

    @Override
    public void savePaypal(Invoice invoice) {
        iInvoiceRepository.savePaypal(invoice.getPrice(), LocalDate.now(),invoice.getUser().getIdUser());
        User user = iUserService.findUserById(invoice.getUser().getIdUser());
        iUserService.updateCoin(user.getCoin() + invoice.getPrice(),invoice.getUser().getIdUser());
        iUserService.updateTypeUser(user.getCoin() + invoice.getPrice(),invoice.getUser().getIdUser());
    }
}
