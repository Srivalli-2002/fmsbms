package com.project.fmsbms.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.fmsbms.entities.Family;
import com.project.fmsbms.entities.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {

	List<Payment> findByFamily(Family family);
}
