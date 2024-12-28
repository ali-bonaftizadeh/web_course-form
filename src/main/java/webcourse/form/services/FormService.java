package webcourse.form.services;

import webcourse.form.dto.FieldDto;
import webcourse.form.dto.FieldRequestDto;
import webcourse.form.dto.FormDto;
import webcourse.form.dto.FormRequestDto;
import webcourse.form.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import webcourse.form.models.Field;
import webcourse.form.models.Form;
import org.springframework.stereotype.Service;
import webcourse.form.repositories.FieldRepository;
import webcourse.form.repositories.FormRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FormService {

    private final FormRepository formRepository;
    private final FieldRepository fieldRepository;

    public FormDto createForm(FormRequestDto form) {
        Form formEntity = formMapper(form);
        formEntity.getFields().forEach(field -> field.setForm(formEntity));
        return formMapper(formRepository.save(formEntity));
    }

    public FormDto getForm(Long id) {
        return formMapper(formRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Form not found")));
    }

    public List<FormDto> getAllForms() {
        return formRepository.findAll().stream().map(this::formMapper).toList();
    }

    public List<FormDto> getAllPublishedForms() {
        return formRepository.findAllByPublishedTrue().stream().map(this::formMapper).toList();
    }

    public FormDto updateForm(Long id, FormRequestDto formDetails) {
        Form existingForm = formRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found"));

        existingForm.setName(formDetails.getName());
        existingForm.setPublished(formDetails.isPublished());

        existingForm.getFields().clear();

        List<Field> updatedFields = formDetails.getFields().stream()
                .map(this::fieldMapper)
                .peek(field -> field.setForm(existingForm))
                .toList();
        existingForm.getFields().addAll(updatedFields);

        Form savedForm = formRepository.save(existingForm);

        return formMapper(savedForm);
    }

    public void deleteForm(Long id) {
        Form form = formRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Form not found"));
        formRepository.delete(form);
    }

    public List<FieldDto> getFieldsForForm(Long formId) {
        List<Field> fieldsByFormId = fieldRepository.findByFormId(formId);
        return fieldsByFormId.stream().map(this::fieldMapper).toList();
    }

    public void publishForm(Long formId) {
        Form form = formRepository.findById(formId).orElseThrow(() -> new ResourceNotFoundException("Form not found"));
        form.setPublished(true);
        formRepository.save(form);
    }

    public FormDto formMapper(Form form){
        return FormDto.builder()
                .name(form.getName())
                .id(form.getId())
                .published(form.isPublished())
                .fields(form.getFields().stream().map(this::fieldMapper).toList())
                .build();
    }

    public Form formMapper(FormRequestDto form){
        return Form.builder()
                .name(form.getName())
                .published(form.isPublished())
                .fields(form.getFields().stream().map(this::fieldMapper).toList())
                .build();
    }

    public FieldDto fieldMapper(Field field){
        return FieldDto.builder()
                .label(field.getLabel())
                .type(field.getType())
                .defaultValue(field.getDefaultValue())
                .name(field.getName())
                .id(field.getId()).build();
    }

    public Field fieldMapper(FieldRequestDto field){
        return Field.builder()
                .label(field.getLabel())
                .type(field.getType())
                .defaultValue(field.getDefaultValue())
                .name(field.getName())
                .build();
    }
}

