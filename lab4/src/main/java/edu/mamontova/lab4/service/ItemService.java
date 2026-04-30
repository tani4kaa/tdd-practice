package edu.mamontova.lab4.service;

import edu.mamontova.lab4.model.Item;
import edu.mamontova.lab4.model.PaginationMetaData;
import edu.mamontova.lab4.repository.ItemRepository;
import edu.mamontova.lab4.request.ItemPageRequest;
import edu.mamontova.lab4.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public ApiResponse<PaginationMetaData, Item> getItemsPage(ItemPageRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by(Sort.Direction.DESC, "id"));

        Page<Item> page = itemRepository.findAll(pageable);

        PaginationMetaData metadata = PaginationMetaData.builder()
                .number(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .code(200)
                .build();

        return new ApiResponse<>(metadata, page.getContent());
    }
}