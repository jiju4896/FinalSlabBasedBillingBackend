package com.slabBased.project.controller;

import java.util.List;

import com.slabBased.project.entity.SlabPeriod;
import com.slabBased.project.entity.Slabs;

import com.slabBased.project.services.BillServices;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.slabBased.project.entity.Bill;



@RestController
@RequestMapping("/billingSystem")
@CrossOrigin("*")
public class BillsController {
    @Autowired
    BillServices billServices;


    //New API's

    @PostMapping("/slabPeriodInitializer")
    public Object addSlabPeriod(@RequestBody SlabPeriod slabPeriod) {


        return billServices.addSlabPeriod(slabPeriod);
    }

    @PostMapping("/slabInitializer")
    @ResponseStatus(HttpStatus.CREATED)
    public Object addSlab(@RequestBody Slabs slab) {


        return billServices.addSlab(slab);
    }

    @PostMapping("/billgenerator")
    @ResponseStatus(HttpStatus.CREATED)
    public String billGeneration(@RequestBody Bill bill) {

        return billServices.BillCalculator(bill);
    }


    //OLD API's
    @GetMapping("/bills")
    public List<Bill> readBillByUser(@RequestParam Long userId) {

        return billServices.getAllBillsByUserId(userId);
    }

    @GetMapping("/lastBills")

    public Bill readLastBill(@RequestParam Long userId) {

        return billServices.getLastBillOfCurrentUser(userId);

    }

    @GetMapping("/bill/{id}")
    public Bill getBillById(@PathVariable(value = "id") long id) {
        return billServices.getBillDetailsByBillId(id);
    }

}
