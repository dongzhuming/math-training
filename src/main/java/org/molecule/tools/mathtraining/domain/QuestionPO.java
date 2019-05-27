package org.molecule.tools.mathtraining.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Dong Zhuming
 */
@Entity
@Data
@Table(name = "question")
public class QuestionPO {
    private static final String ITEM_DELIMITER = "|";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "org.molecule.tools.mathtraining.util.SnowFlakeIdGenerator")
    private Long id;

    private String code;

    private String title;

    private String answer;

    private String items;

    @CreationTimestamp
    private LocalDateTime createdDate;

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

    public void addWrong() {
        //do nothing
    }
}
