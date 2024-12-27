package webcourse.form.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldDto {
    private Long id;
    private String name;
    private String type;
    private String label;
    private String defaultValue;
}