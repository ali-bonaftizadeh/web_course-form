package webcourse.form.models;

import jakarta.persistence.*;
import lombok.*;
import webcourse.form.enums.FieldType;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private FieldType type;

    private String label;

    private String defaultValue;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;
}
