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
import com.fun.rms.DTO.AddEmployeeDTO;
import com.fun.rms.DTO.UpdateLoginCodeDTO;
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
	public ResponseEntity<String> login(@Valid @RequestBody String loginCode) {
		try {
			if (service.loginCodeExists(loginCode)) {
				return new ResponseEntity<String>(Response.SUCCES.toString(), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(Response.WRONG_CREDENTIALS.toString(), HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(Response.SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping()
	public ResponseEntity<String> addEmployee(@RequestBody AddEmployeeDTO dto) {
		try {
			if (service.managerLoginCodeIsCorrect(dto.getManagerLoginCode())) {
				if (!service.loginCodeExists(dto.getLoginCode())) {
					service.addEmployee(dto.getFirstName(), dto.getLastName(), dto.getLoginCode());
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

	@PutMapping(path = "/{id}/name")
	public ResponseEntity<String> updateName(@PathVariable Integer id,
			@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName) {
		try {
			service.updateName(id, firstName, lastName);
			return new ResponseEntity<String>(Response.SUCCES.toString(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(Response.SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(path = "/{id}/loginCode")
	public ResponseEntity<String> updateLoginCode(@PathVariable Integer id, @RequestBody UpdateLoginCodeDTO dto) {
		try {
			if (service.loginCodeIsCorrect(id, dto.getOldLoginCode())) {
				if (!service.loginCodeExists(dto.getNewLoginCode())) {
					service.updateLoginCode(id, dto.getNewLoginCode());
					return new ResponseEntity<String>(Response.SUCCES.toString(), HttpStatus.OK);
				}
				return new ResponseEntity<String>(Response.LOGIN_CODE_IN_USE.toString(), HttpStatus.IM_USED);
			}
			return new ResponseEntity<String>(Response.WRONG_CREDENTIALS.toString(), HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<String>(Response.SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(path = "/{id}/role")
	public ResponseEntity<String> updateRole(@PathVariable Integer id, @RequestBody UpdateUserRoleDTO dto) {
		try {
			if (!dto.getRole().equals(Role.MANAGER)) {
				if (!service.userAlreadyInRole(id, dto.getRole())) {
					if (service.managerLoginCodeIsCorrect(dto.getManagerLoginCode())) {
						service.updateRole(id, dto.getRole());
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

	@PutMapping(path = "/{managerToBeId}/passManagerRole")
	public ResponseEntity<String> passManagerRole(@PathVariable Integer managerToBeId,
			@RequestParam(value = "managerLoginCode") String managerLoginCode) {

		try {
			if (service.managerLoginCodeIsCorrect(managerLoginCode)) {
				if (service.userIsAssistantManager(managerToBeId)) {
					service.passManagerRole(managerToBeId);
					return new ResponseEntity<String>(Response.SUCCES.toString(), HttpStatus.OK);
				}
				return new ResponseEntity<String>(Response.NOT_ASSISTANT_MANAGER.toString(), HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<String>(Response.WRONG_CREDENTIALS.toString(), HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<String>(Response.SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
