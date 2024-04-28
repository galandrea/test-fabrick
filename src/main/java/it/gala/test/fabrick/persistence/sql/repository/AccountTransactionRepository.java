package it.gala.test.fabrick.persistence.sql.repository;

import it.gala.test.fabrick.persistence.sql.entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, String> {
    boolean existsByTransactionId(String transactionId);
}
