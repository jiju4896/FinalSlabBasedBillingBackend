package com.slabBased.project.controller;

import com.slabBased.project.entity.Bill;
import com.slabBased.project.entity.SlabPeriod;
import com.slabBased.project.entity.Slabs;
import com.slabBased.project.services.Implementation.BillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/billing-system")
@CrossOrigin("*")
public class BillsController {
    @Autowired
    BillServiceImpl billServices;


    //New APIs
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/slab-period/initializer")
    public String addSlabPeriod(@RequestBody SlabPeriod slabPeriod) throws RuntimeException {


        return billServices.addSlabPeriod(slabPeriod);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("{slabPeriodId}/slab/initializer")
    @ResponseStatus(HttpStatus.CREATED)
    public String addSlab(@PathVariable(value = "slabPeriodId") Long slabPeriodId, @RequestBody Slabs slabRequest) throws  RuntimeException {


        return billServices.addSlab(slabPeriodId, slabRequest);
    }

    @PreAuthorize("hasRole('ADMIN','USER')")
    @GetMapping("/read/slab-period")
    public List<SlabPeriod> readAllSlabPeriod() throws  RuntimeException {

        return billServices.getAllSlabPeriod();
    }

    @PreAuthorize("hasRole('ADMIN','USER')")
    @PostMapping("/bill/generator")
    @ResponseStatus(HttpStatus.CREATED)
    public String billGeneration(@RequestBody Bill bill) throws  RuntimeException {

        return billServices.billCalculator(bill);
    }


    //OLD APIs
    @PreAuthorize("hasRole('ADMIN','USER')")
    @GetMapping("/bills/{userId}")
    public List<Bill> readBillByUser(@PathVariable(value = "userId") Long userId) throws  RuntimeException {

        return billServices.getAllBillsByUserId(userId);
    }

    @PreAuthorize("hasRole('ADMIN','USER')")
    @GetMapping("/lastBills/{userId}")

    public Bill readLastBill(@PathVariable(value = "userId") Long userId) throws  RuntimeException {

        return billServices.getLastBillOfCurrentUser(userId);

    }

    @PreAuthorize("hasRole('ADMIN','USER')")
    @GetMapping("/bill/{id}")
    public Bill getBillById(@PathVariable(value = "id") long id) throws  RuntimeException {
        return billServices.getBillDetailsByBillId(id);
    }

}
