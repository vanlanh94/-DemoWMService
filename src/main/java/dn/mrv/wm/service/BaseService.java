package dn.mrv.wm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    List<T> findAll();
    Optional<T> findById(long id);
    T create(T entity);
    T update(T entity); // todo: handle exception
    boolean delete(long id);
}
