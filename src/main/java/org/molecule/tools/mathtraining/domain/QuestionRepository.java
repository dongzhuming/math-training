package org.molecule.tools.mathtraining.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Dong Zhuming
 */
public interface QuestionRepository extends JpaRepository<QuestionPO, Integer> {

    Optional<QuestionPO> findFirstByCode(String code);
    Stream<QuestionPO> findAllByCodeIn(Collection<String> codes);
}
