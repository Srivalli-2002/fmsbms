package com.project.fmsbms.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.fmsbms.entities.Billing;

@Repository
public interface BillingRepository extends CrudRepository<Billing, Integer>{

}
