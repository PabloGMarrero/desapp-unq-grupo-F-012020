package unq.tpi.desapp.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import unq.tpi.desapp.model.Store

@Repository
interface StoreRepository: JpaRepository<Store, Long>{
}