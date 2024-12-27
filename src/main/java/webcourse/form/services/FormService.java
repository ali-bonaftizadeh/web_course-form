package webcourse.form.services;

import webcourse.form.dto.FormDto;
import webcourse.form.dto.FieldDto;
import webcourse.form.models.Form;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormService {

    // Mock data for demonstration
    private final List<Form> forms = new ArrayList<>();

    public List<FormDto> getAllForms() {
        // Convert entities to DTOs
        return forms.stream()
                .map(form -> new FormDto(form.getId(), form.getName(), form.isPublished()))
                .collect(Collectors.toList());
    }

    public FormDto createForm(FormDto formDto) {
        // Convert DTO to entity, save it, then return the saved entity as DTO
        Form form = Form.builder().id(formDto.getId()).name(formDto.getName())
                .published(formDto.isPublished()).build();
        forms.add(form);
        return new FormDto(form.getId(), form.getName(), form.isPublished());
    }

    public FormDto getForm(Long id) {
        // Find the form by ID and convert it to a DTO
        Form form = forms.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Form not found"));
        return new FormDto(form.getId(), form.getName(), form.isPublished());
    }

    public FormDto updateForm(Long id, FormDto formDto) {
        // Find the form, update its fields, and convert it back to a DTO
        Form form = forms.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Form not found"));

        form.setName(formDto.getName());
        form.setPublished(formDto.isPublished());

        return new FormDto(form.getId(), form.getName(), form.isPublished());
    }

    public void deleteForm(Long id) {
        // Remove the form by ID
        forms.removeIf(form -> form.getId().equals(id));
    }

    public void publishForm(Long id) {
        // Find the form and mark it as published
        Form form = forms.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Form not found"));

        form.setPublished(true);
    }

    public List<FieldDto> getFieldsForForm(Long formId) {
        // For demonstration, we assume each form has fields
        Form form = forms.stream()
                .filter(f -> f.getId().equals(formId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Form not found"));

        return form.getFields().stream()
                .map(field -> new FieldDto(
                        field.getId(), field.getName(), field.getType(), field.getLabel(), field.getDefaultValue()))
                .collect(Collectors.toList());
    }
}
