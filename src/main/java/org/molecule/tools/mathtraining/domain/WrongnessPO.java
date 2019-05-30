package org.molecule.tools.mathtraining.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Dong Zhuming
 */
@Entity
@Table(name = "wrongness")
@Data
public class WrongnessPO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
    @GenericGenerator(name = "id", strategy = "org.molecule.tools.mathtraining.util.SnowFlakeIdGenerator")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;


    private String name;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "code", referencedColumnName = "code", nullable = false, updatable = false)
    private QuestionPO question;

}
