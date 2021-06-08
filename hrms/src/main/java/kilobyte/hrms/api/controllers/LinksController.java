package kilobyte.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.LinkService;
import kilobyte.hrms.entities.concretes.Link;

@RestController
@RequestMapping("/api/links/")
@CrossOrigin
public class LinksController {

	private LinkService linkService;
	
	@Autowired
	public LinksController(LinkService linkService) {
		super();
		this.linkService = linkService;
	}
	
	@PostMapping("addLink")
	public ResponseEntity<?> addLink(@RequestBody Link link) {
		return ResponseEntity.ok(this.linkService.addLink(link));
	}
	
	@GetMapping("getAllLinks")
	public ResponseEntity<?> gettAll() {
		return ResponseEntity.ok(this.linkService.getAll());
	}
	
	@GetMapping("getByUnemployed")
	public ResponseEntity<?> getByUnemployedId(@RequestParam int unemployedId) {
		return ResponseEntity.ok(this.linkService.getByUnemployedId(unemployedId));
	}
}