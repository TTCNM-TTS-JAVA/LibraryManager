package org.library.manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.library.manager.entity.Item;
import org.library.manager.model.ItemRequest;
import org.library.manager.model.ItemResponse;
import org.library.manager.repository.ItemRepository;
import org.library.manager.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Ví dụ về một Service implementation.
 * Package `service/impl` chứa class @Service thực thi logic nghiệp vụ:
 *   - Gọi repository để đọc/ghi dữ liệu.
 *   - Validate dữ liệu đầu vào theo quy tắc nghiệp vụ.
 *   - Ánh xạ giữa Entity và DTO (toResponse, toEntity).
 *
 */
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private static final String STATUS_ACTIVE = "active";
    private static final String STATUS_INACTIVE = "inactive";

    @Override
    public List<ItemResponse> getAll() {
        return itemRepository.findByStatus(STATUS_ACTIVE)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ItemResponse getById(Long id) {
        Item item = findOrThrow(id);
        return toResponse(item);
    }

    @Override
    @Transactional
    public ItemResponse create(ItemRequest request) {
        if (itemRepository.existsByName(request.getName())) {
            throw new RuntimeException("Tên đã tồn tại: " + request.getName());
        }

        Item item = Item.builder()
                .name(request.getName())
                .description(request.getDescription())
                .status(STATUS_ACTIVE)
                .build();

        return toResponse(itemRepository.save(item));
    }

    @Override
    @Transactional
    public ItemResponse update(Long id, ItemRequest request) {
        Item item = findOrThrow(id);

        item.setName(request.getName());
        item.setDescription(request.getDescription());

        return toResponse(itemRepository.save(item));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Item item = findOrThrow(id);
        item.setStatus(STATUS_INACTIVE);
        itemRepository.save(item);
    }

    /** Tìm item theo ID, ném exception nếu không tồn tại */
    private Item findOrThrow(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy item với ID: " + id));
    }

    /** Ánh xạ từ Entity sang Response DTO */
    private ItemResponse toResponse(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .status(item.getStatus())
                .build();
    }
}
