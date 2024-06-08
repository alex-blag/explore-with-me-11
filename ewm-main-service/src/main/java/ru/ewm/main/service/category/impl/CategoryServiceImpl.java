package ru.ewm.main.service.category.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.exception.ExceptionUtil;
import ru.ewm.main.model.Category;
import ru.ewm.main.repository.CategoryRepository;
import ru.ewm.main.service.category.CategoryService;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getByIdOrThrow(long id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> ExceptionUtil.getCategoryNotFoundException(id));
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        if (!categoryRepository.existsById(id)) {
            throw ExceptionUtil.getCategoryNotFoundException(id);
        }
        categoryRepository.deleteById(id);
    }

}
