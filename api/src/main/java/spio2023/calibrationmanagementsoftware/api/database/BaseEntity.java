package spio2023.calibrationmanagementsoftware.api.database;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

}
