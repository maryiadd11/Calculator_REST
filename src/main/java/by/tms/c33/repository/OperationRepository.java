package by.tms.c33.repository;

import by.tms.c33.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    Optional<List<Operation>> findAllByType(String type);
}
