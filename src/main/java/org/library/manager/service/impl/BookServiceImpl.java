package org.library.manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.library.manager.entity.Author;
import org.library.manager.entity.Book;
import org.library.manager.entity.BookCategory;
import org.library.manager.entity.Publisher;
import org.library.manager.enums.Status;
import org.library.manager.exception.CustomException;
import org.library.manager.exception.ErrorCode;
import org.library.manager.model.dto.BookDto;
import org.library.manager.model.request.BookRequest;
import org.library.manager.model.response.BookResponse;
import org.library.manager.repository.AuthorRepository;
import org.library.manager.repository.BookCategoryRepository;
import org.library.manager.repository.BookRepository;
import org.library.manager.repository.PublisherRepository;
import org.library.manager.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookCategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> filterBook(int size,int page,BookDto bookDto) {
        Pageable pageable = PageRequest.of(page - 1,size);
        Set<Long> authorIdSet = null;
        if(bookDto.getFilterAuthor() != null
                && !bookDto.getFilterAuthor().isEmpty()){
            authorIdSet = new HashSet<>();
            for(Long authorId : bookDto.getFilterAuthor()){
                Long id = authorRepository.findById(authorId)
                        .orElseThrow(() ->
                                new CustomException(ErrorCode.AUTHOR_NOT_ALREADY_EXIST))
                        .getId();
                authorIdSet.add(id);
            }
        }
        Set<Long> categoryIdSet = null;
        if(bookDto.getFilterCategory() != null
                && !bookDto.getFilterCategory().isEmpty()){
            categoryIdSet = new HashSet<>();
            for(Long categoryId : bookDto.getFilterCategory()){
                Long id = categoryRepository.findById(categoryId)
                        .orElseThrow(() ->
                                new CustomException(ErrorCode.CATEGORY_NOT_ALREADY_EXIST))
                        .getId();
                categoryIdSet.add(id);
            }
        }

        Long publisherId = null;

        if(bookDto.getFilterPublisher() != null){
            publisherId = publisherRepository.findById(bookDto.getFilterPublisher())
                    .orElseThrow(() ->
                            new CustomException(ErrorCode.PUBLISHER_NOT_ALREADY_EXIST))
                    .getId();
        }
        String search = null;

        if(bookDto.getSearch() != null
                && !bookDto.getSearch().trim().isEmpty()){
            search = bookDto.getSearch().trim();
        }

        Page<Book> books = bookRepository.filterBook(
                search,
                bookDto.getStatus(),
                authorIdSet,
                categoryIdSet,
                publisherId,
                pageable
        );

        return books.stream().map(book ->
                BookResponse.builder()
                        .id(book.getId())
                        .bookCode(book.getBookCode())
                        .bookTitle(book.getBookTitle())
                        .categoryIds(
                                book.getCategories()
                                        .stream()
                                        .map(BookCategory::getId)
                                        .collect(Collectors.toSet())
                        )
                        .authorIds(
                                book.getAuthors()
                                        .stream()
                                        .map(Author::getId)
                                        .collect(Collectors.toSet())
                        )
                        .publisherId(book.getPublisher().getId())
                        .publishedYear(book.getPublishedYear())
                        .totalQuantity(book.getTotalQuantity())
                        .availableQuantity(book.getAvailableQuantity())
                        .shelfLocation(book.getShelfLocation())
                        .status(book.getStatus())
                        .createdAt(book.getCreatedAt())
                        .updatedAt(book.getUpdatedAt())
                        .build()
        ).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponse getBookById(Long bookId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                        new CustomException(ErrorCode.BOOK_NOT_EXISTED));

        return BookResponse.builder()
                .id(book.getId())
                .bookCode(book.getBookCode())
                .bookTitle(book.getBookTitle())
                .categoryIds(
                        book.getCategories()
                                .stream()
                                .map(BookCategory::getId)
                                .collect(Collectors.toSet())
                )
                .authorIds(
                        book.getAuthors()
                                .stream()
                                .map(Author::getId)
                                .collect(Collectors.toSet())
                )
                .publisherId(book.getPublisher().getId())
                .publishedYear(book.getPublishedYear())
                .totalQuantity(book.getTotalQuantity())
                .availableQuantity(book.getAvailableQuantity())
                .shelfLocation(book.getShelfLocation())
                .status(book.getStatus())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }

    @Override
    @Transactional
    public BookResponse createBook(BookRequest request) {

        if(bookRepository.existsByBookCode(request.getBookCode())){
            throw new CustomException(ErrorCode.BOOK_CODE_EXISTED);
        }

        Set<Author> authors = new HashSet<>();

        for(Long authorId : request.getAuthorIds()){

            Author author = authorRepository.findById(authorId)
                    .orElseThrow(() ->
                            new CustomException(ErrorCode.AUTHOR_NOT_ALREADY_EXIST));

            authors.add(author);
        }

        Set<BookCategory> categories = new HashSet<>();

        for(Long categoryId : request.getCategoryIds()){

            BookCategory category = categoryRepository.findById(categoryId)
                    .orElseThrow(() ->
                            new CustomException(ErrorCode.CATEGORY_NOT_ALREADY_EXIST));

            categories.add(category);
        }

        Publisher publisher = publisherRepository.findById(request.getPublisherId())
                .orElseThrow(() ->
                        new CustomException(ErrorCode.PUBLISHER_NOT_ALREADY_EXIST));

        if (request.getTotalQuantity()<request.getAvailableQuantity()){
            throw new CustomException(ErrorCode.INVALID_RETURN_QUANTITY);
        }

        Book book = Book.builder()
                .bookCode(request.getBookCode())
                .bookTitle(request.getBookTitle())
                .authors(authors)
                .categories(categories)
                .publisher(publisher)
                .publishedYear(request.getPublishedYear())
                .totalQuantity(request.getTotalQuantity())
                .availableQuantity(request.getAvailableQuantity())
                .shelfLocation(request.getShelfLocation())
                .status(request.getStatus())
                .build();

        bookRepository.save(book);

        return BookResponse.builder()
                .id(book.getId())
                .bookCode(book.getBookCode())
                .bookTitle(book.getBookTitle())
                .categoryIds(
                        book.getCategories()
                                .stream()
                                .map(BookCategory::getId)
                                .collect(Collectors.toSet())
                )
                .authorIds(
                        book.getAuthors()
                                .stream()
                                .map(Author::getId)
                                .collect(Collectors.toSet())
                )
                .publisherId(book.getPublisher().getId())
                .publishedYear(book.getPublishedYear())
                .totalQuantity(book.getTotalQuantity())
                .availableQuantity(book.getAvailableQuantity())
                .shelfLocation(book.getShelfLocation())
                .status(book.getStatus())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }

    @Override
    @Transactional
    public BookResponse updateBook(Long bookId,BookRequest request) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                        new CustomException(ErrorCode.BOOK_NOT_EXISTED));

        if(bookRepository.existsByBookCode(request.getBookCode())
                && !book.getBookCode().equals(request.getBookCode())){

            throw new CustomException(ErrorCode.BOOK_CODE_EXISTED);
        }

        Set<Author> authors = new HashSet<>();

        for(Long authorId : request.getAuthorIds()){

            Author author = authorRepository.findById(authorId)
                    .orElseThrow(() ->
                            new CustomException(ErrorCode.AUTHOR_NOT_ALREADY_EXIST));

            authors.add(author);
        }

        Set<BookCategory> categories = new HashSet<>();

        for(Long categoryId : request.getCategoryIds()){

            BookCategory category = categoryRepository.findById(categoryId)
                    .orElseThrow(() ->
                            new CustomException(ErrorCode.CATEGORY_NOT_ALREADY_EXIST));

            categories.add(category);
        }

        Publisher publisher = publisherRepository.findById(request.getPublisherId())
                .orElseThrow(() ->
                        new CustomException(ErrorCode.PUBLISHER_NOT_ALREADY_EXIST));

        if (request.getTotalQuantity()<request.getAvailableQuantity()){
            throw new CustomException(ErrorCode.INVALID_RETURN_QUANTITY);
        } else if (request.getTotalQuantity() < (book.getTotalQuantity()-book.getAvailableQuantity())) {
            throw new CustomException(ErrorCode.TOTAL_QUANTITY_LESS_THAN_BORROWED);
        }

        book.setBookCode(request.getBookCode());
        book.setBookTitle(request.getBookTitle());
        book.setAuthors(authors);
        book.setCategories(categories);
        book.setPublisher(publisher);
        book.setPublishedYear(request.getPublishedYear());
        book.setAvailableQuantity(request.getAvailableQuantity());
        book.setAvailableQuantity(request.getAvailableQuantity());
        book.setShelfLocation(request.getShelfLocation());
        book.setStatus(request.getStatus());

        bookRepository.save(book);

        return BookResponse.builder()
                .id(book.getId())
                .bookCode(book.getBookCode())
                .bookTitle(book.getBookTitle())
                .categoryIds(
                        book.getCategories()
                                .stream()
                                .map(BookCategory::getId)
                                .collect(Collectors.toSet())
                )
                .authorIds(
                        book.getAuthors()
                                .stream()
                                .map(Author::getId)
                                .collect(Collectors.toSet())
                )
                .publisherId(book.getPublisher().getId())
                .publishedYear(book.getPublishedYear())
                .totalQuantity(book.getTotalQuantity())
                .availableQuantity(book.getAvailableQuantity())
                .shelfLocation(book.getShelfLocation())
                .status(book.getStatus())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }

    @Override
    public void deleteBook(Long bookId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                        new CustomException(ErrorCode.BOOK_NOT_EXISTED));
        book.setStatus(Status.INACTIVE);
        bookRepository.save(book);
    }
}