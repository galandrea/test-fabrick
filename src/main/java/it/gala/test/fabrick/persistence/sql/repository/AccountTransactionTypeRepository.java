package it.gala.test.fabrick.persistence.sql.repository;

import it.gala.test.fabrick.persistence.sql.entity.AccountTransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountTransactionTypeRepository extends JpaRepository<AccountTransactionType, Long> {
    Optional<AccountTransactionType> findByEnumerationAndValue(String enumeration, String value);
}
