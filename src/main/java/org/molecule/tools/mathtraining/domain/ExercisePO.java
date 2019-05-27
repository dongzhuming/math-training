package org.molecule.tools.mathtraining.domain;

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
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "org.molecule.tools.mathtraining.util.SnowFlakeIdGenerator")
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
