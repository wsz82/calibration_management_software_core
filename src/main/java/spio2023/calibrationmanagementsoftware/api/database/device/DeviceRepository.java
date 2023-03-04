package spio2023.calibrationmanagementsoftware.api.database.device;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<TestDevice, Long> {
}
