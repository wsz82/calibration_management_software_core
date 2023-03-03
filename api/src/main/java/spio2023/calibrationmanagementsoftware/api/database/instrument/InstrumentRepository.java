package spio2023.calibrationmanagementsoftware.api.database.instrument;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentRepository extends JpaRepository<ReferenceInstrument, Long> {
}
