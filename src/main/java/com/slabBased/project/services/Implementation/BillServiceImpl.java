package com.slabBased.project.services.Implementation;

import com.slabBased.project.entity.Bill;
import com.slabBased.project.entity.SlabPeriod;
import com.slabBased.project.entity.Slabs;
import com.slabBased.project.repository.BillRepository;
import com.slabBased.project.repository.SlabPeriodRepository;
import com.slabBased.project.repository.SlabsRepository;
import com.slabBased.project.services.BillService;
import com.slabBased.project.utils.BillCalculatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillRepository billRepository;
    @Autowired
    SlabsRepository slabsRepository;
    @Autowired
    SlabPeriodRepository slabPeriodRepository;
    Double finalAmount = 0.0;
    Double net, sRate;
    Slabs slabs1;
    String resultOutput = " ";

    public String addSlabPeriod(SlabPeriod slabPeriod) {
        slabPeriodRepository.save(slabPeriod);
        return "SLAB PERIOD CREATED";
    }

    public String addSlab(Slabs slab) {
        Slabs slabs = new Slabs();
        slabs.setId(slab.getId());
        slabs.setStartRead(slab.getStartRead());
        slabs.setEndRead(slab.getEndRead());
        slabs.setSlabId(slabPeriodRepository.getLastPeriod().getId());
        slabs.setSlabRate(slab.getSlabRate());
        slabsRepository.save(slabs);

        return "Slab Range And Rate Created";


    }

    public String BillCalculator(Bill bill) {

        Bill lastBill;
        lastBill = billRepository.getLastBillDetails(bill.getUserId());
        Bill finalBill = new Bill();

        List<SlabPeriod> periodList = new ArrayList<>(slabPeriodRepository.findAll());
        if (periodList.isEmpty()){
            resultOutput="Slab Rate not yet created";
        }  else
        {

            for (int i = 0; i < periodList.size(); i++) {
                SlabPeriod slabPeriod1;

                slabPeriod1 = periodList.get(i);
                BillCalculatorUtils periodCalc = new BillCalculatorUtils(slabPeriod1.getFromDate(), slabPeriod1.getToDate(), bill.getBillDate());
                if (periodCalc.isWithinRange()) {
                    List<Slabs> slabslist = new ArrayList<>(slabsRepository.getAllSlabsInCurrentPeriod(slabPeriod1.getId()));
                    for (int j = 0; j < slabslist.size(); ) {
                        slabs1 = slabslist.get(j);
                        BillCalculatorUtils slabCalc = new BillCalculatorUtils(slabs1.getStartRead(), slabs1.getEndRead(), bill.getCurrentRead(), lastBill.getCurrentRead());
                        net = slabCalc.netUnitCalc(bill.getCurrentRead(), lastBill.getCurrentRead());
                        if (slabCalc.slabCheck()) {
                            sRate = slabs1.getSlabRate();
                            BillCalculatorUtils calculation = new BillCalculatorUtils(slabs1.getSlabRate());
                            finalAmount = calculation.billGenerator(net, sRate);
                            resultOutput = "Bill with Total Amount: " + finalAmount + " at a SlabRate of " + sRate + " is Generated";
                            finalBill.setBillAmount(finalAmount);
                            finalBill.setCurrentRead(bill.getCurrentRead());
                            finalBill.setNetUnit(net);
                            finalBill.setSlabRate(sRate);
                            finalBill.setUserId(bill.getUserId());
                            finalBill.setBillDate(bill.getBillDate());
                            billRepository.save(finalBill);
                            break;

                        } else {
                            resultOutput = "Slab Rate For This Range in this Period is Not Defined!";
                            j++;
                        }


                    }

                } else {
                    resultOutput = "Slab Rate for This Period is not Defined!";
                    i++;
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
