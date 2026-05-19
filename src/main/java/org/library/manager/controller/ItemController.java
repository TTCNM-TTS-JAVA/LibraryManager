package org.library.manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.library.manager.exception.BadRequestException;
import org.library.manager.exception.CustomException;
import org.library.manager.exception.ErrorCode;
import org.library.manager.model.ItemRequest;
import org.library.manager.model.ItemResponse;
import org.library.manager.model.request.ExampleRequest;
import org.library.manager.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Ví dụ về một REST Controller.
 * Package `controller` là tầng ngoài cùng, tiếp nhận HTTP request từ client.
 * Nguyên tắc thin controller — controller chỉ làm 3 việc:
 *   1. Nhận input (path variable, request body, query param).
 *   2. Gọi service tương ứng.
 *   3. Trả về HTTP response với status code phù hợp.
 * Không đặt logic nghiệp vụ hay truy vấn DB trực tiếp trong controller.
 */
@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/error")
    public ResponseEntity<?> throwErrorExample(@RequestBody @Valid ExampleRequest exampleRequest) {
        throw new BadRequestException("message.with.error.code");
    }

    @GetMapping("/error-with-args")
    public ResponseEntity<?> throwErrorWithArgsExample() {
        throw new BadRequestException("message.with.args", "Arg1", "Arg2");
    }

    @GetMapping("/custom-error")
    public ResponseEntity<?> throwCustomErrorExample() {
        throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
    /** GET /api/items — lấy toàn bộ danh sách */
    @GetMapping
    public ResponseEntity<List<ItemResponse>> getAll() {
        return ResponseEntity.ok(itemService.getAll());
    }

    /** GET /api/items/{id} — lấy chi tiết theo ID */
    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getById(id));
    }

    /** POST /api/items — tạo mới, trả về 201 Created */
    @PostMapping
    public ResponseEntity<ItemResponse> create(@RequestBody ItemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.create(request));
    }

    /** PUT /api/items/{id} — cập nhật toàn bộ thông tin */
    @PutMapping("/{id}")
    public ResponseEntity<ItemResponse> update(@PathVariable Long id, @RequestBody ItemRequest request) {
        return ResponseEntity.ok(itemService.update(id, request));
    }

    /** DELETE /api/items/{id} — xóa, trả về 204 No Content */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
