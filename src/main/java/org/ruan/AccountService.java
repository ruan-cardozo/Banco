package org.ruan;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public List<Account> getAllAccount() {
		return accountRepository.findAll();
	}

	public Account getAccountById(Long id) {
		Optional<Account> optionalAccount = accountRepository.findById(id);
		return accountRepository.findById(id).orElse(null);
	}

	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}

	public Account updateAccount(Long id, Account account) {
		Optional<Account> optionalAccount = accountRepository.findById(id);
		if (optionalAccount.isPresent()) {
			Account exisitingAccount = optionalAccount.get();
			exisitingAccount.setNome(account.getNome());
			exisitingAccount.setSaldo(account.getSaldo());
			return accountRepository.save(exisitingAccount);
		}
		return null;
	}

	public boolean deleteAccount(Long id) {
		Optional<Account> optionalAccount = accountRepository.findById(id);
		if (optionalAccount.isPresent()) {
			accountRepository.delete(optionalAccount.get());
			return true;
		}
		return false;
	}
}
