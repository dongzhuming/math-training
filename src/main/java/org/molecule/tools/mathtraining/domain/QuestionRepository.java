package org.molecule.tools.mathtraining.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Dong Zhuming
 */
public interface QuestionRepository extends JpaRepository<QuestionPO, Integer> {

    Optional<QuestionPO> findFirstByCode(String code);
}
