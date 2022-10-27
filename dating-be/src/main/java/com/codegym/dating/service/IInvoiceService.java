package com.codegym.dating.service;

import com.codegym.dating.model.Invoice;

public interface IInvoiceService {
    void savePaypal(Invoice invoice);
}
