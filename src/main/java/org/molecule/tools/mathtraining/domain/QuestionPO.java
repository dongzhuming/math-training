package org.molecule.tools.mathtraining.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dong Zhuming
 */
@Entity
@Data
@Table(name = "question")
public class QuestionPO implements Serializable {
    private static final String ITEM_DELIMITER = "|";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
    @GenericGenerator(name = "id", strategy = "org.molecule.tools.mathtraining.util.SnowFlakeIdGenerator")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String code;

    private String title;

    private String answer;

    private String items;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @OneToMany(targetEntity = WrongnessPO.class, mappedBy = "question", cascade = CascadeType.ALL)
    private List<WrongnessPO> wrongness = new ArrayList<>();


    public static QuestionPO create(QuestionVO vo) {
        QuestionPO po = new QuestionPO();
        po.setCode(vo.getCode());
        po.setAnswer(vo.getAnswer());
        po.setTitle(vo.getTitle());
        po.setItems(String.join(ITEM_DELIMITER, vo.getItems()));
        return po;
    }

    public QuestionVO toViewObject() {
        return QuestionVO.builder()
                .items(items.split(ITEM_DELIMITER))
                .answer(answer)
                .title(title)
                .code(code)
                .build();
    }

    public void addWrong(String name) {
        final WrongnessPO wrongnessPO = new WrongnessPO();
        wrongnessPO.setName(name);
        wrongnessPO.setQuestion(this);
        wrongness.add(wrongnessPO);
    }
}
