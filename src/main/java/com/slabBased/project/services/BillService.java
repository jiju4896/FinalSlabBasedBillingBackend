package com.slabBased.project.services;

import com.slabBased.project.entity.Bill;
import com.slabBased.project.entity.SlabPeriod;
import com.slabBased.project.entity.Slabs;

import java.util.List;

public interface BillService {
    String billCalculator(Bill bill);

    List<Bill> getAllBillsByUserId(Long userId);

    Bill getLastBillOfCurrentUser(Long userId);

    Bill getBillDetailsByBillId(long id);

    String addSlabPeriod(SlabPeriod slabPeriod);

    String addSlab(Long SlabPeriodId, Slabs slab);
}
