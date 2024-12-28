package webcourse.form.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import webcourse.form.enums.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FieldRequestDto {
    private String name;
    private FieldType type;
    private String label;
    private String defaultValue;
}