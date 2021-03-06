package org.molecule.tools.mathtraining.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Dong Zhuming
 */
@Entity
@Data
@Table(name = "exercise")
public class ExercisePO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
    @GenericGenerator(name = "id", strategy = "org.molecule.tools.mathtraining.util.SnowFlakeIdGenerator")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime finishTime;

    private Integer initialCount;

    private Integer totalCount;

    private Integer wrongCount;

    private String name;

    public void addWrong() {
        if(wrongCount == null) {
            wrongCount = 1;
        } else {
            wrongCount ++;
        }
    }
}
