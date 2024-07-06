package com.ms.user.repositories;

import com.ms.user.model.AppTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppTestRpository extends CrudRepository<AppTest, Long> {
}
