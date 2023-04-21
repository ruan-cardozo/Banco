package org.ruan;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.zxing.common.BitMatrix;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.net.URI;
@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping
	public List<Account> getAllAccount() {
		return accountService.getAllAccount();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
		Account account = accountService.getAccountById(id);
		if (account == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(account);
	}

	@PostMapping
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		Account createdAccount = accountService.createAccount(account);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
		Account updatedAccount = accountService.updateAccount(id, account);
		if (updatedAccount == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedAccount);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
		boolean success = accountService.deleteAccount(id);
		if (success) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/{id}/deposit")
	public ResponseEntity<Account> deposit(@PathVariable Long id, @RequestBody BigDecimal amount) {
		Account account = accountService.getAccountById(id);
		if (account == null) {
			return ResponseEntity.notFound().build();
		}
		account.setSaldo(account.getSaldo().add(amount));
		Account updateAccount = accountService.updateAccount(id, account);
		return ResponseEntity.ok(updateAccount);
	}


	@PostMapping("/{id}/withdraw")
	public ResponseEntity<Account> withdraw(@PathVariable Long id, @RequestBody BigDecimal amount) {
		Account account = accountService.getAccountById(id);
		if (account == null) {
			return ResponseEntity.notFound().build();
		}
		if (account.getSaldo().compareTo(amount) < 0) {
			return ResponseEntity.badRequest().build();
		}
		account.setSaldo(account.getSaldo().subtract(amount));
		Account updatedAccount = accountService.updateAccount(id, account);
		return ResponseEntity.ok(updatedAccount);
	}

	@PostMapping("/pay")
	public ResponseEntity<byte[]> pay(@RequestBody PaymentRequest paymentRequest) throws IOException, WriterException {
		Account account = accountService.getAccountById(paymentRequest.getAccountId());
		if (account == null) {
			return ResponseEntity.notFound().build();
		}

		BigDecimal valueToPay = paymentRequest.getValue();
		if (account.getSaldo().compareTo(valueToPay) < 0) {
			return ResponseEntity.badRequest().build();
		}
		return null;
	}
}



