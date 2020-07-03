package unq.tpi.desapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import unq.tpi.desapp.model.Item
import unq.tpi.desapp.repository.ItemRepository

@Service
@Transactional
class ItemService  {

    @Autowired
    lateinit var repository:ItemRepository

    fun save(anItem: Item):Item{
        return this.repository.save(anItem)
    }
}