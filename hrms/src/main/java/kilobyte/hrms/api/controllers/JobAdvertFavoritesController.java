package kilobyte.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.JobAdvertFavoriteService;
import kilobyte.hrms.core.utilities.utils.ResponseEntityReturn;

@RestController
@RequestMapping("/api/jobAdvertFavorites/")
@CrossOrigin
public class JobAdvertFavoritesController {

	private JobAdvertFavoriteService advertFavoriteService;
	
	@Autowired
	public JobAdvertFavoritesController(JobAdvertFavoriteService advertFavoriteService) {
		super();
		this.advertFavoriteService = advertFavoriteService;
	}
	
	@PostMapping("addFavorite")
	public ResponseEntity<?> addFavorite(@RequestParam int unemployedId, int advertId) {
		return ResponseEntityReturn.checkResult(this.advertFavoriteService.addFavorite(unemployedId, advertId));
	}
	
	@DeleteMapping("deleteFavorite")
	public ResponseEntity<?> deleteFavorite(@RequestParam int favoriteId) {
		return ResponseEntityReturn.checkResult(this.advertFavoriteService.deleteFavorite(favoriteId));
	}
	
	@GetMapping("getByUnemployed")
	public ResponseEntity<?> getByUnemployedId(@RequestParam int unemployedId) {
		return ResponseEntity.ok(this.advertFavoriteService.getByUnemployedId(unemployedId));
	}
}
