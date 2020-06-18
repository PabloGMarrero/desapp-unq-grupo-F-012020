package unq.tpi.desapp.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import unq.tpi.desapp.Product

@Repository
interface ProductRepository: JpaRepository<Product, Long> {

}