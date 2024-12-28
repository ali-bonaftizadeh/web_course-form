package webcourse.form.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormRequestDto {
    private String name;
    private boolean published;
    private List<FieldRequestDto> fields;
}
