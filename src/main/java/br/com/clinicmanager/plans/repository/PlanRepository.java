package br.com.clinicmanager.plans.repository;

import br.com.clinicmanager.plans.entity.Plan;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PlanRepository implements PanacheRepositoryBase<Plan, UUID> {
    // Métodos adicionais, se necessário, podem ser definidos aqui

    public List<Plan> findByName(String name) {
        return find("name", name).list();
    }

    public List<Plan> findByPriceRange(Double minPrice, Double maxPrice) {
        return find("price >= ?1 and price <= ?2", minPrice, maxPrice).list();
    }
}
