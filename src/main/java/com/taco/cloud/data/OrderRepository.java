package com.taco.cloud.data;
import com.taco.cloud.models.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository
        extends CrudRepository<TacoOrder, Long> {
}
