package unq.tpi.desapp.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import unq.tpi.desapp.model.Store

/**
 * Class to manage the stores
 */
@Repository
interface StoreRepository: JpaRepository<Store, Long>{
}