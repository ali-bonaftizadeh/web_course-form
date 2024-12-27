package webcourse.form.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormDto {
    private Long id;
    private String name;
    private boolean published;
}