package exercise.mapper;

import exercise.dto.CategoryCreateDTO;
import exercise.dto.CategoryDTO;
import exercise.model.Category;
import org.mapstruct.*;

// BEGIN
@Mapper(
        uses= {JsonNullableMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class CategoryMapper {
    public abstract Category map(CategoryCreateDTO createDTO);
    public abstract CategoryDTO map(Category category);
    //public abstract void update(CategoryCreateDTO createDTO, @MappingTarget Category model);
}
// END
