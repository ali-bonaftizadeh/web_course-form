package webcourse.form.controllers;

import webcourse.form.models.Field;
import webcourse.form.models.Form;
import org.springframework.web.bind.annotation.*;
import webcourse.form.services.FormService;

import java.util.List;


@RestController
@RequestMapping("/api/forms")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping
    public List<Form> getAllForms() {
        return formService.getAllForms();
    }

    @PostMapping
    public Form createForm(
            @RequestBody Form form) {
        return formService.createForm(form);
    }

    @GetMapping("/{id}")
    public Form getForm(
            @PathVariable Long id) {
        return formService.getForm(id);
    }

    @PutMapping("/{id}")
    public Form updateForm(
            @PathVariable Long id,
            @RequestBody Form form) {
        return formService.updateForm(id, form);
    }

    @DeleteMapping("/{id}")
    public void deleteForm(
            @PathVariable Long id) {
        formService.deleteForm(id);
    }

    @PostMapping("/{id}/publish")
    public void publishForm(
            @PathVariable Long id) {
        formService.publishForm(id);
    }

    @GetMapping("/{id}/fields")
    public List<Field> getFieldsForForm(
            @PathVariable Long id) {
        return formService.getFieldsForForm(id);
    }
}
