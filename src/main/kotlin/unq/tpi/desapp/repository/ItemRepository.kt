package unq.tpi.desapp.repository

import org.springframework.data.jpa.repository.JpaRepository
import unq.tpi.desapp.model.Item

interface ItemRepository : JpaRepository<Item, Long> {
}