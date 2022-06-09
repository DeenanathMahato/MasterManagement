package com.management.system.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.management.system.dto.EmployeeDTO;
import com.management.system.errors.BadRequestAlertException;
import com.management.system.errors.HeaderUtil;
import com.management.system.errors.ResponseUtil;
import com.management.system.repository.EmployeeRepository;
import com.management.system.service.EmployeeService;
import com.management.system.utils.PaginationUtil;

/**
 * REST controller for managing {@link com.management.syestem.domain.Employee}.
 */
@RestController
@RequestMapping("/api")
public class EmployeeResource {

	private final Logger log = LoggerFactory.getLogger(EmployeeResource.class);

	private static final String ENTITY_NAME = "mastermanagementEmployee";

	@Value("${management.clientApp.name}")
	private String applicationName;

	private final EmployeeService employeeService;

	private final EmployeeRepository employeeRepository;

	public EmployeeResource(EmployeeService employeeService, EmployeeRepository employeeRepository) {
		this.employeeService = employeeService;
		this.employeeRepository = employeeRepository;
	}

	/**
	 * {@code POST  /employees} : Create a new employee.
	 *
	 * @param employeeDTO the employeeDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new employeeDTO, or with status {@code 400 (Bad Request)} if
	 *         the employee has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/employees")
	public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) throws URISyntaxException {
		log.debug("REST request to save Employee : {}", employeeDTO);
		if (employeeDTO.getId() != null) {
			throw new BadRequestAlertException("A new employee cannot already have an ID", ENTITY_NAME, "idexists");
		}
		EmployeeDTO result = employeeService.save(employeeDTO);
		return ResponseEntity
				.created(new URI("/api/employees/" + result.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	/**
	 * {@code PUT  /employees/:id} : Updates an existing employee.
	 *
	 * @param id          the id of the employeeDTO to save.
	 * @param employeeDTO the employeeDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated employeeDTO, or with status {@code 400 (Bad Request)} if
	 *         the employeeDTO is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the employeeDTO couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/employees/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable(value = "id", required = false) final Long id,
			@RequestBody EmployeeDTO employeeDTO) throws URISyntaxException {
		log.debug("REST request to update Employee : {}, {}", id, employeeDTO);
		if (employeeDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, employeeDTO.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!employeeRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		EmployeeDTO result = employeeService.update(employeeDTO);
		return ResponseEntity.ok().headers(
				HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, employeeDTO.getId().toString()))
				.body(result);
	}

	/**
	 * {@code PATCH  /employees/:id} : Partial updates given fields of an existing
	 * employee, field will ignore if it is null
	 *
	 * @param id          the id of the employeeDTO to save.
	 * @param employeeDTO the employeeDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated employeeDTO, or with status {@code 400 (Bad Request)} if
	 *         the employeeDTO is not valid, or with status {@code 404 (Not Found)}
	 *         if the employeeDTO is not found, or with status
	 *         {@code 500 (Internal Server Error)} if the employeeDTO couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PatchMapping(value = "/employees/{id}", consumes = { "application/json", "application/merge-patch+json" })
	public ResponseEntity<EmployeeDTO> partialUpdateEmployee(
			@PathVariable(value = "id", required = false) final Long id, @RequestBody EmployeeDTO employeeDTO)
			throws URISyntaxException {
		log.debug("REST request to partial update Employee partially : {}, {}", id, employeeDTO);
		if (employeeDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, employeeDTO.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!employeeRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		Optional<EmployeeDTO> result = employeeService.partialUpdate(employeeDTO);

		return ResponseUtil.wrapOrNotFound(result,
				HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, employeeDTO.getId().toString()));
	}

	/**
	 * {@code GET  /employees} : get all the employees.
	 *
	 * @param pageable the pagination information.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of employees in body.
	 */
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(Pageable pageable) {
		log.debug("REST request to get a page of Employees");
		Page<EmployeeDTO> page = employeeService.findAll(pageable);
		HttpHeaders headers = PaginationUtil
				.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * {@code GET  /employees/:id} : get the "id" employee.
	 *
	 * @param id the id of the employeeDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the employeeDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) {
		log.debug("REST request to get Employee : {}", id);
		Optional<EmployeeDTO> employeeDTO = employeeService.findOne(id);
		return ResponseUtil.wrapOrNotFound(employeeDTO);
	}

	/**
	 * {@code DELETE  /employees/:id} : delete the "id" employee.
	 *
	 * @param id the id of the employeeDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		log.debug("REST request to delete Employee : {}", id);
		employeeService.delete(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
				.build();
	}
}
