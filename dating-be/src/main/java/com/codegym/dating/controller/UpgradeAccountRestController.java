package com.codegym.dating.controller;

import com.codegym.dating.dto.InvoiceDto;
import com.codegym.dating.model.Invoice;
import com.codegym.dating.model.User;
import com.codegym.dating.service.IInvoiceService;
import com.codegym.dating.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("api/users/upgradeAccount")
public class UpgradeAccountRestController {
    @Autowired
    private IInvoiceService iInvoiceService;
    @Autowired
    private IUserService iUserService;

    @GetMapping("/detailUser/{idUser}")
    public ResponseEntity<User> findById(@PathVariable Integer idUser) {
        User user = iUserService.findUserById(idUser);
        if (user.getIdUser()==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> savePaypal(@RequestBody @Valid InvoiceDto invoiceDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (invoiceDto.getPrice() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceDto, invoice);
        invoice.setPrice(Integer.parseInt(invoiceDto.getPrice()));
        iInvoiceService.savePaypal(invoice);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
