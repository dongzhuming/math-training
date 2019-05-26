package org.molecule.tools.mathtraining.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author Dong Zhuming
 */
@Entity
@Data
@Table(name = "exercise")
public class ExercisePO {
    @Id
    @GeneratedValue
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
