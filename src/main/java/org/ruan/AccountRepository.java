package org.ruan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.SplittableRandom;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	List<Account> findAccountById(String id);

	List<Account> findAccountByNome(String nome);

}
