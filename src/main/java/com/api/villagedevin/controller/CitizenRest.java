package com.api.villagedevin.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.villagedevin.model.persistence.Citizen;
import com.api.villagedevin.model.transport.CitizenDTO;
import com.api.villagedevin.service.CitizenService;

@RestController
@RequestMapping("/citizens")
public class CitizenRest {

	private final CitizenService citizenService;

	public CitizenRest(CitizenService citizenService) {
		this.citizenService = citizenService;
	}

	@GetMapping("/list-all")
	public List<CitizenDTO> listAll() {
		return citizenService.listAll();
	}

	@GetMapping("/list-names")
	public List<String> listNames() {
		return citizenService.listCitizensNames();
	}

	@GetMapping("/list-citizens/{id}")
	public Citizen listCitizensDetails(@PathVariable("id") Integer id) {
		return citizenService.listCitizens(id);
	}

	@GetMapping("/{id}")
	public CitizenDTO getById(@PathVariable("id") Integer id) {
		return citizenService.getById(id);
	}

	@GetMapping("/filter")
	public List<CitizenDTO> getCitizensByFilter(@RequestParam("name") String name) {
		return citizenService.getCitizensByName(name);
	}

	@GetMapping("/filter-month")
	public List<CitizenDTO> getCitizensByMonth(@RequestParam("month") Integer month) {
		return citizenService.getCitizensByMonth(month);
	}

	@GetMapping("/filter-age")
	public List<CitizenDTO> getCitizensByAge(@RequestParam("age") Integer age) {
		return citizenService.getCitizensByAge(age);
	}

//	
//	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<HttpStatus> create(@RequestBody Citizen citizen) {
		return this.citizenService.create(citizen);
	}

//	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
	return this.citizenService.delete(id);
	}

//	
//	@PreAuthorize("hasAnyRole('ADMIN')")
//	@GetMapping("/village-report")
//	public VillageReportDTO getReport() throws SQLException, CitizensNotFoundException, IllegalAccessException {
//		return citizenService.getReport();
//	}

}
