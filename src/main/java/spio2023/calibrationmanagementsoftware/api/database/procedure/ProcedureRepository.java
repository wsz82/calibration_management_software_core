package spio2023.calibrationmanagementsoftware.api.database.procedure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcedureRepository extends JpaRepository<ProcedureData, Long> {
}
