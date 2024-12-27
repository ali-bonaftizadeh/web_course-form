package services;

import exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import models.Field;
import models.Form;
import org.springframework.stereotype.Service;
import repositories.FieldRepository;
import repositories.FormRepository;

import java.util.List;


@Service
@RequiredArgsConstructor // Lombok generates constructor for final fields
public class FormService {

    private final FormRepository formRepository;
    private final FieldRepository fieldRepository;

    public Form createForm(Form form) {
        return formRepository.save(form);
    }

    public Form getForm(Long id) {
        return formRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Form not found"));
    }

    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    public Form updateForm(Long id, Form formDetails) {
        Form form = formRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Form not found"));
        form.setName(formDetails.getName());
        form.setPublished(formDetails.isPublished());
        return formRepository.save(form);
    }

    public void deleteForm(Long id) {
        Form form = formRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Form not found"));
        formRepository.delete(form);
    }

    public List<Field> getFieldsForForm(Long formId) {
        return fieldRepository.findByFormId(formId);
    }

    public void publishForm(Long formId) {
        Form form = formRepository.findById(formId).orElseThrow(() -> new ResourceNotFoundException("Form not found"));
        form.setPublished(true);
        formRepository.save(form);
    }
}

