package jpabook.jpashop.service;


import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    
    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, UpdateItemDto itemDto) {
        
        // 가져올때 영속성 엔티티를 가져온다.
        // jpa의 merge 호출시 수행되는 코드와 같다. 근데 merge는 입력으로 들어오지 않은 필드값은 null로 바꾼다.
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(itemDto.getPrice());
        findItem.setName(itemDto.getName());
        findItem.setStockQuantity(itemDto.getStockQuantity());
        // 트랜잭션 커밋 시점에 변경이 감지되면 flush가 일어나서 업데이트 쿼리를 알아서 날려준다.
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
