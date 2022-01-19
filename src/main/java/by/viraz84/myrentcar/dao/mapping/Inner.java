package by.viraz84.myrentcar.dao.mapping;

import java.util.List;

public interface Inner<T> {
    List<T> findAllFullNotClose();
}
