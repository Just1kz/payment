package FirstSpringBootApplication.repository;

import FirstSpringBootApplication.domain.PaymentPhone;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepo extends CrudRepository<PaymentPhone, Long> {
    Iterable<PaymentPhone> findById(String id);

}
