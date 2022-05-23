package com.slabBased.project.controller;
import java.util.List;

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
import com.slabBased.project.repository.BillRepository;

@RestController
@RequestMapping("/billingSystem")
@CrossOrigin("*")
public class BillsController {
	@Autowired
	BillRepository bRepo;

	@GetMapping("/bills")
	@CrossOrigin("http://localhost:3000/")
	public List<Bill> readBillByUser(@RequestParam String username) {

		return bRepo.findAllByUsername(username);
	}

	@PostMapping("/addbills")
	@ResponseStatus(HttpStatus.CREATED)
	public Bill addBill(@RequestBody Bill bill) {
		return bRepo.save(bill);
	}

	@GetMapping("/lastbills")

	public Bill readLastbill(@RequestParam String username) {
		return bRepo.getLastBillDetails(username);

	}

	@GetMapping("/bill/{id}")
	public Bill getBillById(@PathVariable(value = "id") long id) {
		return bRepo.findById(id);
	}

}
