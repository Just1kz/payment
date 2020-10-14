package FirstSpringBootApplication.repository;

import FirstSpringBootApplication.domain.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepo extends CrudRepository<Account, Long> {
    List<Account> findByRequisite(String requisite);
}
