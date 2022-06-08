package com.slabBased.project.controller;

import java.util.ArrayList;
import java.util.List;

import com.slabBased.project.entity.Slabperiod;
import com.slabBased.project.entity.Slabs;
import com.slabBased.project.repository.SlabsRepository;
import com.slabBased.project.repository.SlabperiodRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.slabBased.project.calculator.BillCalculator;
import com.slabBased.project.entity.Bill;


import com.slabBased.project.repository.BillRepository;

@RestController
@RequestMapping("/billingSystem")
@CrossOrigin("*")
public class BillsController {
	@Autowired
	BillRepository bRepo;
	@Autowired
	SlabsRepository sRepo;
	@Autowired
	SlabperiodRepository speriodRepo;


	
	//New API's
	@PostMapping("/slabinitializer")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Slabs> addSlab(@RequestBody Slabs slab) {
		Slabperiod speriod ;
		speriod = getLastPeriod();


		Slabs slabs=new Slabs();
		slabs.setId(slab.getId());
		slabs.setStartread(slab.getStartread());
		slabs.setEndread(slab.getEndread());
		slab.setSlabid(speriod.getId());
		sRepo.save(slabs);


		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	private Slabperiod getLastPeriod() {
		return speriodRepo.getLastPeriod();
	}


	@PostMapping("/slabperiodinitializer")
	public  ResponseEntity<Slabperiod> addSlab(@RequestBody Slabperiod slabperiod) {
		speriodRepo.save(slabperiod);


		return new ResponseEntity<>(slabperiod,HttpStatus.CREATED);
	}

	@PostMapping("/billgenerator")
	@ResponseStatus(HttpStatus.CREATED)
	public Bill billGeneration(@RequestBody Bill bill) {
		Bill lastbill ;
		Slabperiod slabperiod1;
		Slabs slabs1;
		lastbill = readLastbill(bill.getUserid());
		Bill fbill=new Bill();



		List<Slabperiod> periodlist=new ArrayList<>(speriodRepo.findAll());
		for(int i=0;i< periodlist.size();i++){

			slabperiod1=periodlist.get(i);
			BillCalculator periodCalc=new BillCalculator(slabperiod1.getFromdate(),slabperiod1.getTodate(),bill.getCurrentdate());
			if(periodCalc.isWithinRange()){
			List<Slabs> slabslist=new ArrayList<>(sRepo.getAllSlabsInCurrentPeriod(slabperiod1.getId()));
			for(int j=0;j<slabslist.size();){
				slabs1=slabslist.get(j);
				BillCalculator slabCalc= new BillCalculator(slabs1.getStartread(),slabs1.getEndread(), bill.getCurread(),lastbill.getCurread());
				Double net = slabCalc.netUnitCalc();
				if(slabCalc.slabCheck()){
					BillCalculator calculation= new BillCalculator(slabs1.getValue());


					fbill.setBillamount(calculation.billGenerator());
					fbill.setCurread(bill.getCurread());
					fbill.setNetunit(net);
					fbill.setSlabrate(slabs1.getValue());
					fbill.setUserid(bill.getUserid());
					fbill.setCurrentdate(bill.getCurrentdate());
					bRepo.save(fbill);
					break;

				}else{
					j++;
				}


			}

			}else {
				i++;
			}
		}
		return fbill;







	}

	@GetMapping("/slabrates")
	public List<Slabs> readSlab() {

		return sRepo.findAll();
	}

	/*@GetMapping("/currentslabrate")
	public List<Slabs> readSlabByCurrentYear(@RequestParam int year) {
		return sRepo.findAllByYear(year);
	}

	//OLD API's
	@GetMapping("/bills")
	public List<Bill> readBillByUser(@RequestParam String userid) {

		return bRepo.findAllByUsername(userid);
	}

	@PostMapping("/addbills")
	@ResponseStatus(HttpStatus.CREATED)
	public Bill addBill(@RequestBody Bill bill) {
		return bRepo.save(bill);
	}
*/
	@GetMapping("/lastbills")

	public Bill readLastbill(@RequestParam Long userid) {

		return bRepo.getLastBillDetails(userid);

	}

	@GetMapping("/bill/{id}")
	public Bill getBillById(@PathVariable(value = "id") long id) {
		return bRepo.findById(id);
	}

}
