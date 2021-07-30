package kilobyte.hrms.core.utilities.utils;

import org.springframework.http.ResponseEntity;

import kilobyte.hrms.core.utilities.results.Result;

public class ResponseEntityReturn {

	public static ResponseEntity<?> checkResult(Result operation) {
		Result result = operation;
		if(result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}
}
