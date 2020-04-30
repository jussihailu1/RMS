package com.fun.rms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fun.rms.service.UserService;
import com.fun.rms.DTO.UpdateUserRoleDTO;
import com.fun.rms.DTO.addEmployeeDTO;
import com.fun.rms.enums.Response;
import com.fun.rms.enums.Role;
import com.fun.rms.model.User;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping(path = "/login")
	public ResponseEntity<String> login(@Valid @RequestBody String logincode) {
		try {
			if (service.login(logincode)) {
				return new ResponseEntity<String>(Response.SUCCES.toString(), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(Response.WRONG_CREDENTIALS.toString(), HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(Response.SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping()
	public ResponseEntity<String> addEmployee(@RequestBody addEmployeeDTO data) {
		try {
			if (service.checkManagerLoginCode(data.getManagerLoginCode())) {
				if (service.checkLoginCodeAvailability(data.getLoginCode())) {
					service.addEmployee(data.getFirstName(), data.getLastName(), data.getLoginCode());
					return new ResponseEntity<String>(Response.SUCCES.toString(), HttpStatus.CREATED);
				}
				return new ResponseEntity<String>(Response.LOGIN_CODE_IN_USE.toString(), HttpStatus.IM_USED);
			}
			return new ResponseEntity<String>(Response.WRONG_CREDENTIALS.toString(), HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<String>(Response.SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping()
	public ResponseEntity<?> getAll() {
		try {
			return new ResponseEntity<List<User>>(service.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(Response.SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(path = "/updateRole/{id}")
	public ResponseEntity<String> updateRole(@PathVariable Integer id, @RequestBody UpdateUserRoleDTO data) {
		try {
			if (!data.getRole().equals(Role.MANAGER)) {
				if (!service.userAlreadyInRole(id, data.getRole())) {
					if (service.checkManagerLoginCode(data.getManagerLoginCode())) {
						service.updateRole(id, data.getRole());
						return new ResponseEntity<String>(Response.SUCCES.toString(), HttpStatus.OK);
					}
					return new ResponseEntity<String>(Response.WRONG_CREDENTIALS.toString(), HttpStatus.UNAUTHORIZED);
				}
				return new ResponseEntity<String>(Response.ALREADY_IN_ROLE.toString(), HttpStatus.IM_USED);
			}
			return new ResponseEntity<String>(Response.WRONG_ROLE.toString(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<String>(Response.SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	Dit kan misschien beter met een request body maar ik wou ook ergens query parameters gebruiken.
	@PutMapping(path = "/passManagerRole/{managerToBeId}")
	public ResponseEntity<String> passManagerRole(@PathVariable Integer managerToBeId,
			@RequestParam(value = "managerLoginCode") String managerLoginCode) {
		try {
			if (service.userIsAssistantManager(managerToBeId)) {
				if (service.checkManagerLoginCode(managerLoginCode)) {
					service.passManagerRole(managerToBeId);
					return new ResponseEntity<String>(Response.SUCCES.toString(), HttpStatus.OK);
				}
				return new ResponseEntity<String>(Response.WRONG_CREDENTIALS.toString(), HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<String>(Response.NOT_ASSISTANT_MANAGER.toString(), HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<String>(Response.SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
