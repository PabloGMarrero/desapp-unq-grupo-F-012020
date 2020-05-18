package unq.tpi.desapp.repositories


import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import unq.tpi.desapp.model.Store


@Repository
interface StoreRepository : CrudRepository<Store?, Int?> {
    fun findById(id: String?): Store?
}