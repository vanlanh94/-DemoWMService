package dn.mrv.wm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Base Repository
 * @param <T> Entity
 * @param <ID> Id type
 */
@NoRepositoryBean
public interface MyBaseRepository <T, ID extends Serializable> extends JpaRepository<T, ID> {

}
