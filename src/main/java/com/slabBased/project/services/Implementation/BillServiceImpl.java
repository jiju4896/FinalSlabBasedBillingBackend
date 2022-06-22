package com.slabBased.project.services.Implementation;

import com.slabBased.project.entity.Bill;
import com.slabBased.project.entity.SlabPeriod;
import com.slabBased.project.entity.Slabs;
import com.slabBased.project.entity.User;
import com.slabBased.project.repository.BillRepository;
import com.slabBased.project.repository.SlabPeriodRepository;
import com.slabBased.project.repository.UserRepository;
import com.slabBased.project.services.BillService;
import com.slabBased.project.utils.BillCalculatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillRepository billRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SlabPeriodRepository slabPeriodRepository;


    Double finalAmount = 0.0;
    Double net, sRate;

    String resultOutput = " ";

    public String addSlabPeriod(SlabPeriod slabPeriod) {
        slabPeriodRepository.save(slabPeriod);
        return "SLAB PERIOD CREATED";
    }


    public String addSlab(Long slabPeriodId, Slabs slabRequest) {
        try {
            Slabs slabs = new Slabs();
            slabs.setStartRead(slabRequest.getStartRead());
            slabs.setEndRead(slabRequest.getEndRead());
            slabs.setSlabRate(slabRequest.getSlabRate());
            SlabPeriod slabPeriod1 = slabPeriodRepository.findAllById(slabPeriodId);
            slabPeriod1.getSlabsSet().add(slabs);
            slabPeriodRepository.save(slabPeriod1);
        } catch (Exception e) {
            System.out.println("Unable to SAVE");

        }


        return "Slab Range And Rate Created";


    }

    public List<SlabPeriod> getAllSlabPeriod() {
        return slabPeriodRepository.findAll();
    }

    public String BillCalculator(Bill bill) {

        Bill lastBill, finalLastBill = new Bill();
        lastBill = billRepository.getLastBillDetails(bill.getUserId());
        if (lastBill == null) {
            finalLastBill.setCurrentRead(0.0);

        } else {
            finalLastBill.setCurrentRead(lastBill.getCurrentRead());
        }
        Bill finalBill = new Bill();

        List<SlabPeriod> periodList = new ArrayList<>(slabPeriodRepository.findAll());
        if (periodList.isEmpty()) {
            resultOutput = "Slab Rate not yet created";
        } else {

            for (SlabPeriod slabPeriod1 : periodList) {

                BillCalculatorUtils periodCalc = new BillCalculatorUtils(slabPeriod1.getFromDate(), slabPeriod1.getToDate(), bill.getBillDate());
                if (periodCalc.isWithinRange()) {
                    Set<Slabs> slabsList = slabPeriod1.getSlabsSet();
                    for (Slabs slabs1 : slabsList) {

                        BillCalculatorUtils slabCalc = new BillCalculatorUtils(slabs1.getStartRead(), slabs1.getEndRead(), bill.getCurrentRead(), finalLastBill.getCurrentRead());

                        net = slabCalc.netUnitCalc(bill.getCurrentRead(), finalLastBill.getCurrentRead());
                        if (slabCalc.slabCheck()) {
                            sRate = slabs1.getSlabRate();
                            BillCalculatorUtils calculation = new BillCalculatorUtils(slabs1.getSlabRate());
                            finalAmount = calculation.billGenerator(net, sRate);
                            resultOutput = "Bill with Total Amount: " + finalAmount + " at a SlabRate of " + sRate + "for NetUnit " + net + "  is Generated";
                            finalBill.setBillAmount(finalAmount);
                            finalBill.setCurrentRead(bill.getCurrentRead());
                            finalBill.setNetUnit(net);
                            finalBill.setSlabRate(sRate);
                            finalBill.setUserId(bill.getUserId());
                            finalBill.setBillDate(bill.getBillDate());
                            User usr = userRepository.findAllById(bill.getUserId());
                            usr.getBill().add(finalBill);
                            userRepository.save(usr);


                            break;

                        } else {
                            resultOutput = "Slab Rate For This Range in this Period is Not Defined!";

                        }


                    }

                } else {
                    resultOutput = "Slab Rate for This Period is not Defined!";

                }
            }
        }


        return resultOutput;
    }

    public List<Bill> getAllBillsByUserId(Long userId) {
        return billRepository.findAllByUserId(userId);
    }

    public Bill getLastBillOfCurrentUser(Long userId) {
        return billRepository.getLastBillDetails(userId);
    }

    public Bill getBillDetailsByBillId(long id) {
        return billRepository.findById(id);
    }


}
