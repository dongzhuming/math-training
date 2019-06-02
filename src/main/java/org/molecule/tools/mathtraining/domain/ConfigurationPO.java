package org.molecule.tools.mathtraining.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.molecule.tools.mathtraining.config.ExerciseConfig;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Dong Zhuming
 */
@Entity
@Table(name="configuration")
@Data
public class ConfigurationPO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
    @GenericGenerator(name = "id", strategy = "org.molecule.tools.mathtraining.util.SnowFlakeIdGenerator")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String name;

    @Convert(converter = ExerciseConfigConverter.class)
    private ExerciseConfig properties;

    private LocalDateTime createdDate = LocalDateTime.now();
}

