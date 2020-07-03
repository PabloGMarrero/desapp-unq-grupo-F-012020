package unq.tpi.desapp.repository

import org.springframework.data.jpa.repository.JpaRepository
import unq.tpi.desapp.model.Purchase

/**
 *
 */
interface PurchaseRepository: JpaRepository<Purchase, Long> {
}