package com.taco.cloud.data;

import com.taco.cloud.models.Taco;
import com.taco.cloud.models.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

}
