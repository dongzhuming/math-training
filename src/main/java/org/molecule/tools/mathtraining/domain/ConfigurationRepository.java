package org.molecule.tools.mathtraining.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Dong Zhuming
 */
public interface ConfigurationRepository extends JpaRepository<ConfigurationPO, Long> {
    /**
     * @param name 用户名
     * @return 配置
     */
    Optional<ConfigurationPO> findFirstByName(String name);
}
